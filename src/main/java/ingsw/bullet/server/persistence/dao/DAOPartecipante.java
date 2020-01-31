package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.server.model.Partecipante;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPartecipante implements DAOInterface<Partecipante> {

    private DataSource dataSource;

    public DAOPartecipante(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Partecipante t) {
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "INSERT INTO partecipante (email, id_evento, presente) VALUES (?,?,?);";
            statement = connection.prepareStatement(query);

            statement.setString(1, t.getEmail());
            statement.setInt(2, t.getIdEvento());
            statement.setBoolean(3, t.isPresente());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public Partecipante findByPrimaryKey(Object... keys) {
        Connection connection = null;
        Partecipante partecipante = null;

        // parser chiavi primarie...
        String email = (String)keys[0];
        int id_evento = (Integer)keys[1];

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "SELECT * FROM partecipante WHERE partecipante.email = ? AND partecipante.id_evento = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setInt(2, id_evento);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setIdEvento(result.getInt("id_evento"));
                partecipante.setPresente(result.getBoolean("presente"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return partecipante;
    }

    public List<Partecipante> findByEvento(int id_evento) {
        Connection connection = null;
        Partecipante partecipante = null;
        List<Partecipante> partecipanti = new ArrayList<Partecipante>();

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "SELECT * FROM partecipante WHERE partecipante.id_evento = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id_evento);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setIdEvento(result.getInt("id_evento"));
                partecipante.setPresente(result.getBoolean("presente"));
                partecipanti.add(partecipante);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return partecipanti;
    }

    public List<Partecipante> findAll() {
        Connection connection = null;
        Partecipante partecipante = null;
        List<Partecipante> partecipanti = new ArrayList<Partecipante>();

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "SELECT * FROM partecipante";
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setIdEvento(result.getInt("id_evento"));
                partecipante.setPresente(result.getBoolean("presente"));
                partecipanti.add(partecipante);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return partecipanti;
    }

    public void update(Partecipante t) {
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "UPDATE partecipante SET " +
                    "partecipante.presente = ? " +
                    "WHERE partecipante.email = ? " +
                    "AND partecipante.id_evento = ? ";
            statement = connection.prepareStatement(query);

            statement.setBoolean(1, t.isPresente());
            statement.setString(2, t.getEmail());
            statement.setInt(3, t.getIdEvento());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public void delete(Partecipante t) {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
            String delete = "DELETE FROM partecipante WHERE partecipante.email = ? AND partecipante.id_evento = ?";
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setString(1, t.getEmail());
            statement.setInt(2, t.getIdEvento());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

    }
}
