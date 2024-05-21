package br.edu.fateczl.Produto_JavaWebSpring.controller;

import br.edu.fateczl.Produto_JavaWebSpring.model.Produto;
import br.edu.fateczl.Produto_JavaWebSpring.persistence.GenericDao;
import br.edu.fateczl.Produto_JavaWebSpring.persistence.ProdutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    GenericDao gDao;

    @Autowired
    ProdutoDao pDao;

    @RequestMapping(name = "index", value = "/index", method = RequestMethod.GET)
    public ModelAndView doGet(ModelMap model) {
        return new ModelAndView("index");
    }

    @RequestMapping(name = "index", value = "/index", method = RequestMethod.POST)
    public ModelAndView doPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
        // Entrada
        String cmd = allRequestParam.get("botao");
        String qtd = allRequestParam.get("quantidade");

        // Retorno
        String saida= "";
        String erro= "";
        List<Produto> produtos= new ArrayList<>();
        int quantidade = -1;


        try {
            if (qtd != ""){
                if (cmd.equals("Buscar quantidade de Produtos abaixo do estoque")) {
                    quantidade = pDao.quantidadeBaixa(Integer.parseInt(qtd));
                }
                else if (cmd.equals("Buscar Produtos")) {
                    produtos = pDao.produtoQuantidade(Integer.parseInt(qtd));
                }
            }
            else {
                erro = "Digite a quantidade";
            }
        } catch (SQLException | ClassNotFoundException e){
            erro = e.getMessage();
        }
        finally {
            model.addAttribute("produtos", produtos);
            model.addAttribute("quantidade", quantidade);
            model.addAttribute("saida", saida);
            model.addAttribute("erro", erro);
        }

        return new ModelAndView("index");
    }
}
