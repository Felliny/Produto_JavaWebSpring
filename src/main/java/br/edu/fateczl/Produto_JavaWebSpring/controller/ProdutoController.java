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
public class ProdutoController {

    @Autowired
    GenericDao gDao;

    @Autowired
    ProdutoDao pDao;

    @RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
    public ModelAndView produtoGet(ModelMap model) {
        return new ModelAndView("produto");
    }

    @RequestMapping(name = "produto", value = "/produto", method = RequestMethod.POST)
    public ModelAndView produtoPost(@RequestParam Map<String, String> allRequestParam, ModelMap model) {
        // Entrada
        String cmd = allRequestParam.get("botao");
        String qtd = allRequestParam.get("qtd");

        // Retorno
        String saida= "";
        String erro= "";
        List<Produto> produtos= new ArrayList<>();
        String quantidade = "";


//        try {
//            if (cmd.equals("MostrarQuantidade")) {
//
//            }
//            else if (cmd.equals("ListarProdutos")) {
//
//            }
//        } catch (SQLException | ClassNotFoundException e){
//            erro = e.getMessage();
//        }
//        finally {
//            model.addAttribute("produtos", produtos);
//            model.addAttribute("quantidade", quantidade);
//            model.addAttribute("saida", saida);
//            model.addAttribute("erro", erro);
//        }


        return new ModelAndView("produto");
    }
}
