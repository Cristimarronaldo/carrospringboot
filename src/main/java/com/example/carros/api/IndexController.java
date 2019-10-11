package com.example.carros.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get(){
        return "API dos Carros";
    }

    @GetMapping("/userInfo")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user){
        return user;
    }


    /*@GetMapping("/teste")
    public String teste(){
        return "Testando Método";
    }

    @GetMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("senha") String senha){
        return "Login "+login+" ,senha "+senha;
    }

    @GetMapping("/carros/{id}")
    public String getCarrosById(@PathVariable("id") Long id){
        return "Carro "+id;
    }

    @GetMapping("/carros/tipo/{tipo}")
    public String getCarroByTipo(@PathVariable("tipo") String tipo){
        return "Lista de Carros "+tipo;
    }

    @GetMapping("/login2")
    public String login2(@RequestParam("login") String login, @RequestParam("senha") String senha){
        return "Login "+login+" ,senha "+senha;
    }

    */
}
