package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.tarefas.dao.ConnectionFactory;

public class TestConnection {

	 public static void main(String[] args)throws SQLException {    
         try{    
         ConnectionFactory connection = new ConnectionFactory();    
         Connection conexao = connection.getConnection();    
          System.out.println("Conectou!");    
          }catch(SQLException e ){    
             System.out.println("Erro: "+ e.getLocalizedMessage());    
           }    
             }    
}

