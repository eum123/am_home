package com.am.homework;

//import com.amore.sde.component.cache.CategoryCacheManager;
//import com.amore.sde.component.cache.ProductCacheManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class MainApplication {
//	@Autowired
//	private ProductCacheManager productCacheManager;
//
//	@Autowired
//	private CategoryCacheManager categoryCacheManager;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void doCacheManager() {
//		productCacheManager.init();
//		categoryCacheManager.init();
//	}
}
