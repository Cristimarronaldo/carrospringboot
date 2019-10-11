package com.example.carros;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarroServiceTests {

    @Autowired
    private CarroService service;

    @Test
    public void testSave(){
        Carro carro = new Carro();
        carro.setNome("Ferrari");
        carro.setTipo("esportivos");

        CarroDTO c = service.insert(carro);
        assertNotNull(c);

        Long id = c.getId();
        assertNotNull(id);
        c = service.getCarrosById(id);
        assertNotNull(c);

        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());

        service.delete(id);

        //Verificar se deletou
        try {
            assertNull(service.getCarrosById(id));
            fail("O carro não foi excluido.");
        }
        catch (ObjectNotFoundException e){
            // ok
        }
    }

    @Test
    public void testLista(){
        List<CarroDTO> carros = service.getCarros();

        assertEquals(30, carros.size());
    }

    @Test
    public void testGet(){
        CarroDTO c = service.getCarrosById(11L);

        assertNull(c);

        assertEquals("Ferrari FF", c.getNome());
    }

	@Test
	public void testListaPorTipo() {
        assertEquals(10, service.getCarrosByTipo("esportivos").size());

        assertEquals(10, service.getCarrosByTipo("classicos").size());

        assertEquals(10, service.getCarrosByTipo("luxo").size());

        assertEquals(10, service.getCarrosByTipo("x").size());
	}

}