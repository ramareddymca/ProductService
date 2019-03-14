package com.epam.course;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.course.model.Product;
import com.epam.course.repository.ProductRepository;
import com.epam.course.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

	@Mock
	private ProductRepository prodRepo;

	@InjectMocks
	private ProductServiceImpl productService;

	Product mockProd1 = new Product(1, "prod name1", "prod desc1");
	Product mockProd2 = new Product(2, "prod name2", "prod desc2");

	@Test
	public void getMyProductsTest() {
		List<Product> mockProdList = new ArrayList<>();
		mockProdList.add(mockProd2);
		mockProdList.add(mockProd1);

		when(prodRepo.findAll()).thenReturn(mockProdList);
		assertEquals(mockProdList, productService.getProducts());
	}

	@Test
	public void getMyProductTest() {

		when(prodRepo.findById(anyLong())).thenReturn(Optional.of(mockProd1));
		assertEquals(mockProd1, productService.getProduct(1L).get());

	}

	@Test
	public void saveMyProductTest() {

		when(prodRepo.save(mockProd1)).thenReturn(mockProd1);
		assertEquals(mockProd1, productService.saveProduct(mockProd1));

	}	

	@Test(expected = NoSuchElementException.class)
	public void testNoSuchElementException() {

		when(prodRepo.findById(anyLong())).thenReturn(Optional.empty());
		Optional<Product> prod = productService.getProduct(1L);
		prod.get();
	}

}
