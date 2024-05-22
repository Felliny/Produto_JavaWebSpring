package br.edu.fateczl.Produto_JavaWebSpring.controller;

import br.edu.fateczl.Produto_JavaWebSpring.model.Produto;
import br.edu.fateczl.Produto_JavaWebSpring.persistence.GenericDao;
import br.edu.fateczl.Produto_JavaWebSpring.persistence.ProdutoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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

    @RequestMapping(name = "produtoRelatorio", value = "/produtoRelatorio", method = RequestMethod.POST)
    public ResponseEntity relatorioPost(@RequestParam Map<String, String> allRequestParam) {
        String erro= "";
        Map<String, Object> paramRelatorio = new HashMap<String, Object>();
        paramRelatorio.put("quant", allRequestParam.get("quantidade"));

        byte[] bytes= null;

        //Inicializar os elementos do response
        InputStreamResource resource = null;
        HttpStatus status = null;
        HttpHeaders header = new HttpHeaders();

        try {
            Connection connection = gDao.getConnection();
            File file = ResourceUtils.getFile("classpath:reports/RelatorioProduto.jasper");
            JasperReport report= (JasperReport) JRLoader.loadObjectFromFile(file.getAbsolutePath());
            bytes= JasperRunManager.runReportToPdf(report, paramRelatorio, connection);
        } catch (ClassNotFoundException | SQLException | FileNotFoundException | JRException e) {
            e.printStackTrace();
            erro = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        } finally {
            if (erro.equals("")) {
                InputStream inputStream = new ByteArrayInputStream(bytes);
                resource = new InputStreamResource(inputStream);
                header.setContentLength(bytes.length);
                header.setContentType(MediaType.APPLICATION_PDF);
                status = HttpStatus.OK;
            }
        }

        return new ResponseEntity(resource, header, status);
    }
}
