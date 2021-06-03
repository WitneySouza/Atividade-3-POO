package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Aluno;

public class AlunoJDBC {
	
	Connection con;
	String sql;
	PreparedStatement pst;
	
	public Aluno buscarAluno(int id) throws IOException{
		
		
		try {
		con = db.getConexao();
		pst = this.con.prepareStatement("SELECT * FROM aluno WHERE id = ?");
		pst.setInt(1,id);
		ResultSet res = pst.executeQuery();
		
		while(res.next()) {
			
			Aluno aluno = new Aluno();
			aluno.setId(res.getInt("id"));
			aluno.setNome(res.getString("nome"));
			aluno.setSexo(res.getString("sexo"));
			aluno.setDt_nasc(res.getDate("dt_nasc"));
			
			return aluno;
		}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;		
	}
	
	public void salvar(Aluno a) throws IOException {
		
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "INSERT INTO aluno (id,nome, sexo, dt_nasc) VALUES (?, ?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setInt(1, a.getId());
			pst.setString(2, a.getNome());
			pst.setString(3, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(4, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws IOException {
		
		
		try {
		con = db.getConexao();
		List<Aluno> alunos = new ArrayList<Aluno>();
		PreparedStatement pst = this.con.prepareStatement("SELECT * FROM aluno");
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(rs.getInt("id"));
			aluno.setNome(rs.getString("nome"));
			aluno.setSexo(rs.getString("sexo"));
			aluno.setDt_nasc(rs.getDate("dt_nasc"));
			alunos.add(aluno);
			
			
		}
		
		return alunos;
		
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void apagar(int id) throws IOException  {
		
		if(buscarAluno(id) == null ) {
			System.out.println("Nao existe aluno cadastrado no sistema!");
			
		}else {
		try {
		
				con = db.getConexao();
				
		
				String sqlDeleta = "DELETE FROM aluno where id = ?";
				PreparedStatement pstDeleta = con.prepareStatement(sqlDeleta);
				pstDeleta.setInt(1, id);
				pstDeleta.execute();
				
				System.out.println("Aluno Excluído com Sucesso!");

			
			} catch (IOException | SQLException e) {
			
			e.printStackTrace();
			}
		}
		
	}
	
	public void alterar(Aluno a) {
		
		try {
			con = db.getConexao();
			sql = "UPDATE aluno SET nome = ?," +
			"sexo = ?," +
			"dt_nasc = ?"+
			"WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3,dataSql);
			pst.setInt(4, a.getId());
			pst.executeUpdate();
				
			
		} catch (IOException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
}

