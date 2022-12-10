package com.time7.bolaodacopa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.time7.bolaodacopa.models.SelecaoModel;
import com.time7.bolaodacopa.models.SelecaoRepository;
import java.util.List;

@Controller
public class SelecaoController {

    @Autowired
    SelecaoRepository repo;

    @GetMapping(value = "/selecoes")
    public String getSelecoes(Model model){
        
        model.addAttribute("page", "selecoes"); 
        List<SelecaoModel> selecoes = (List<SelecaoModel>)repo.findAll();
        model.addAttribute("selecoes", selecoes);

        return "index";
    }

    @PostMapping(value = "/addselecao")
    public String addSelecao(SelecaoModel selecao, Model model, BindingResult result){

        if(result.hasErrors()){
            return "/selecoes";
        }

        repo.save(selecao);

        return "/selecoes";

    }

}
