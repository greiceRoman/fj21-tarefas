package br.com.caelum.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import br.com.caelum.tarefas.dao.ConnectionFactory;
import br.com.caelum.tarefas.model.Tarefa;

public class JdbcTarefaDao {

	public void adiciona(Tarefa tarefa) throws SQLException {
		// TODO Auto-generated method stub
			
			Connection con = new ConnectionFactory().getConnection(); 
			
	        // cria um preparedStatement
	        String sql = "insert into tarefas" +
	                " (descricao, finalizado, dataFinalizacao)" +
	                " values (?,?,?)";  
		     try {
		    	 
		         PreparedStatement stmt = con.prepareStatement(sql);
		         stmt.setString(1, tarefa.getDescricao());
		         stmt.setBoolean(2,tarefa.isFinalizado());
		         stmt.setDate(3, new Date(Calendar.getInstance().getTimeInMillis())); 
		         stmt.execute();
		         stmt.close();
		         con.close(); 
		        
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 
	}
	public java.util.List<Tarefa> Lista() throws SQLException {
		Connection conexao = new ConnectionFactory().getConnection();
		
         
		String sql = "select * from tarefas";
		 
		     try {
		    	 java.util.List<Tarefa> tarefas = new ArrayList<Tarefa>();
		    	 PreparedStatement xstmt = conexao.prepareStatement(sql);
		    	
		         ResultSet rs = xstmt.executeQuery();
		 
		         while (rs.next()) {
		             // criando o objeto Contato
		        	 Tarefa tarefa = new Tarefa();
		        	 tarefa.setId(rs.getLong("id"));
		        	 tarefa.setDescricao(rs.getString("descricao"));
		        	 tarefa.setFinalizado(true);
		        	 tarefa.setDataFinalizacao(null);
		 
		             // adicionando o objeto à lista
		             tarefas.add(tarefa);
		         }
		         rs.close();
		         xstmt.close();
		         return tarefas;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
	}
	public void remove(Tarefa tarefa) {
		// TODO Auto-generated method stub
		try {
	    	 Connection con = new ConnectionFactory().getConnection(); 
			PreparedStatement stmt = con.prepareStatement("delete from tarefa where id=?");
	         stmt.setLong(1, tarefa.getId());
	         stmt.execute();
	         stmt.close();
	         con.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	public Object buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	public void altera(Tarefa tarefa) {
		// TODO Auto-generated method stub
		
	}


}
