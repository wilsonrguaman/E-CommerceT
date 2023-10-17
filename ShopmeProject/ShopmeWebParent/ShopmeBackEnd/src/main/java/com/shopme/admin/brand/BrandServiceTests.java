/*package com.shopme.admin.brand;

import static org.assertj.core.api.Assertions.assertThat;

import javax.naming.spi.DirStateFactory.Result;

import com.shopme.common.entity.Brand;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BrandServiceTests {
	
	@MockBean private BrandRepository repo;
	@InjectMocks private BrandService service;
	
	@Test
	public void testCheckUniqueInNewModeReturnDuplicate() {
		Integer id = null;
		String name = "Acer";
		
		Brand brand = new Brand(name);
		
		Mockito.when(repo.findByName(name)).thenReturn(brand);
		
		String result = service.checkUnique(id, name);
		assertThat(result).isqualTo("Duplicate");
	}
	
	@Test
	public void testCheckUniqueInEditModeReturnDuplicate() {
		Integer id = null;
		String name = "AMD";
		
		Mockito.when(repo.findByName(name)).thenReturn(null);
		
		String resultString = service.checkUnique(id, name);
		assertThat(result).isEqualTo("OK");
	}
	@Test
	public void testCheckUniqueInEditModeReturnDuplicate() {
		Integer id = 1;
		String name = "Canon";
		Brand brand = new Brand(id, name);
		
		Mockito.when(repo.findByName(name)).thenReturn(brand);
		
		String result = service.checkUnique(2, "Canon");
		assertThat(result.isEqualTo("Duplicate"));
	}
	@Test
	public void testCheckUniqueInNewModeReturnOK() {
		Integer id = null;
		String name = "AMD";
		
		Mockito.when(repo.findByName(name)).thenReturn(null);
		
		String result = service.checkUnique(id, name);
		assertThat(result).isEquealTo("OK");
	}
	@Test
	public void testCheckUniqueInEditModeReturnDuplicate() {
		Integer id= 1;
		String name = "Canon";
		Brand brand = new Brand(id, name);
		
		Mockito.when(repo.findByName(name)).thenReturn(brand);
		String result = service.checkUnique(2, "Canon");
		assertThat(result).isEqualTo("Duplicate");
	}
	@Test
	public void testCheckUniqueInEditModeReturnOK() {
		Integer id = 1;
		String name = "Acer";
		Brand brand = new Brand(id, name);
		
		Mockito.when(repo.findByName(name)).thenReturn(brand);
		
	}
}
*/