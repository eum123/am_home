package com.am.homework.cache.service;

import com.am.homework.cache.entity.ProductEntity;
import com.am.homework.cache.model.Product;
import com.am.homework.cache.repository.ProductRepository;
import com.am.homework.cache.util.ProductHelper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements ApplicationListener<ContextRefreshedEvent> {

	private ProductCache productCache = new ProductCache();

	private final ProductRepository productRepository;

	/**
	 * 상품 정보 조회.
	 * @param productNo
	 * @return
	 * @throws Exception
	 */
    public Product getProductByProductId(long productNo) throws InterruptedException {
    	if (productCache.containProductNo(productNo)) {
			return productCache.getProduct(productNo);
		}
		//select db
		Optional<ProductEntity> result = productRepository.findById(productNo);

		//update cache
		if (result.isPresent()) {
			Product product = ProductHelper.createByEntity(result.get());

			//update cache
			productCache.add(product);
			return product;
		}
		return null;
    }

    /**
     * 카테고리에 해당하는 상품 목록 조회.
     * @param categoryNo
     * @return
     * @throws Exception
     */
    public List<Product> getProductListByCategoryId(int categoryNo) throws Exception {
    	List<Product> list = Lists.newArrayList();

		productCache.getProductNoList(categoryNo).forEach(x -> {
			try {
				if (getProductByProductId(x) != null) {
					list.add(getProductByProductId(x));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});

		return list;
    }

    /**
     * 상품 정보 update.
     * Admin에서 수정된 정보를 반영하기 위한 method
     * @param productNo
     * @return
     */
    public Product updateProduct(long productNo) {
    	ProductEntity entity = productRepository.findById(productNo).orElse(null);
		if(entity != null) {
			Product product = ProductHelper.createByEntity(entity);
			productCache.update(product);

			return product;
		}
		return null;
    }

    @Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		updateAllProduct();
    }

    private void updateAllProduct() {
		productRepository.findAll().forEach(t -> this.productCache.add(ProductHelper.createByEntity(t)));
	}

    /**
     * 전체 데이터 reset.
     */
	public void resetAll() {
		// 테이블에 update 날짜를 추가 하여 변경된 데이터만 갱신한다.
		//아래는 전체 변경으로 적용함.

		productCache.reset();
		updateAllProduct();
	}
}