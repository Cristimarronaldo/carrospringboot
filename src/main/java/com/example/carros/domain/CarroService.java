package com.example.carros.domain;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros(){
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarrosById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado."));

        //return rep.findById(id).map(CarroDTO::create);
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public List<Carro> getCarrosFake(){
        List<Carro> carros = new ArrayList<Carro>();
       // carros.add(new Carro(1L, "Fusca"));
        //carros.add(new Carro(2L, "Brasilia"));
        //carros.add(new Carro(3L, "Chevett"));
        return carros;
    }


    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o carro");
        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o carro");

        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent())
        {
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            rep.save(db);
            return CarroDTO.create(db);
        }
        else {
           return  null;
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);

        /*if(getCarrosById(id).isPresent())
        {
            rep.deleteById(id);
            return true;
        }
        return false;*/
    }
}
