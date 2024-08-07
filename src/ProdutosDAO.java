/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> listagemVendas = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {
        try {
            Connection conn = conectaDAO.conectar();
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES  (?,?,?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            int verificar = prep.executeUpdate();
            conectaDAO.encerrar(conn);
            return verificar > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        try {
            Connection conn = conectaDAO.conectar();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            conectaDAO.encerrar(conn);
            return listagem;
        } catch (SQLException e) {
            return null;
        }
    }

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            Connection conn = conectaDAO.conectar();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            int verificar = prep.executeUpdate();
            if (verificar > 0) {
                JOptionPane.showMessageDialog(null, "Produto com ID " + id + " foi vendido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto encontrado com o ID " + id);
            }
        } catch (SQLException e) {
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        try {
            Connection conn = conectaDAO.conectar();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagemVendas.add(produto);
            }
            conectaDAO.encerrar(conn);
            return listagemVendas;
        } catch (SQLException e) {
            return null;
        }
    }
}
