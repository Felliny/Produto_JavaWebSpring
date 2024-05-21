package br.edu.fateczl.Produto_JavaWebSpring.persistence;

import br.edu.fateczl.Produto_JavaWebSpring.model.Produto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoDao {

    private GenericDao dao;

    public ProdutoDao(GenericDao dao) {
        this.dao = dao;
    }

    public int quantidadeBaixa(int quantidade) throws SQLException, ClassNotFoundException {
        int quantidadeBaixa = 0;

        Connection c= dao.getConnection();
        String sql= """
                { ? = call fnQuantidadeBaixa(?) }
                """;
        CallableStatement cstm = c.prepareCall(sql);
        cstm.setInt(2, quantidade);

        // Registrar o tipo do parâmetro de saída
        cstm.registerOutParameter(1, java.sql.Types.INTEGER);

        cstm.execute();

        quantidadeBaixa = cstm.getInt(1);

        return quantidadeBaixa;
    }

    public List<Produto> produtoQuantidade(int quantidade) throws SQLException, ClassNotFoundException {
        List<Produto> produtos= new ArrayList<>();

        Connection c= dao.getConnection();
        String sql= """
                SELECT * FROM dbo.fnProdutosQuantidade(?)
                """;
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, quantidade);

        ResultSet rs= ps.executeQuery();

        while (rs.next()) {
            Produto p= new Produto();

            p.setCodigo(rs.getInt(1));
            p.setNome(rs.getString(2));
            p.setQuantidade(rs.getInt(3));

            produtos.add(p);
        }
        ps.close();
        rs.close();
        c.close();

        return produtos;
    }
}
