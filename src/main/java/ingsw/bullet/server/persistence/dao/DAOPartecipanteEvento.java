package ingsw.bullet.server.persistence.dao;

import ingsw.bullet.model.Partecipante;
import ingsw.bullet.server.persistence.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPartecipanteEvento implements DAOInterface<Partecipante> {

    private DataSource dataSource;

    public DAOPartecipanteEvento(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Partecipante t) {
        Connection connection = null;

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "INSERT INTO partecipante_evento (email, id_evento, presente) VALUES (?,?,?);";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, t.getEmail());
            statement.setInt(2, t.getId());
            statement.setBoolean(3, t.isPresente());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            String email=resultSet.getString(2);
            t = findByPrimaryKey(email,id);

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
        int id = (Integer)keys[1];

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "SELECT * FROM partecipante_evento " +
                    "WHERE partecipante_evento.email = ? " +
                    "AND partecipante_evento.id_evento = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setInt(2, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setId(result.getInt("id_evento"));
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

    public List<Partecipante> findByUtente(String email) {
        Connection connection = null;
        Partecipante partecipante = null;
        List<Partecipante> partecipanti = new ArrayList<Partecipante>();

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "SELECT * FROM partecipante_evento WHERE partecipante_evento.email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setId(result.getInt("id_evento"));
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

    public List<Partecipante> findByEvento(int id_evento) {
        Connection connection = null;
        Partecipante partecipante = null;
        List<Partecipante> partecipanti = new ArrayList<Partecipante>();

        try {
            connection = this.dataSource.getConnection();
            PreparedStatement statement;
            String query = "SELECT * FROM partecipante_evento WHERE partecipante_evento.id_evento = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id_evento);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setId(result.getInt("id_evento"));
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
            String query = "SELECT * FROM partecipante_evento";
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                partecipante = new Partecipante();
                partecipante.setEmail(result.getString("email"));
                partecipante.setId(result.getInt("id_evento"));
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
            String query = "UPDATE partecipante_evento SET " +
                    "partecipante_evento.presente = ? " +
                    "WHERE partecipante_evento.email = ? " +
                    "AND partecipante_evento.id_evento = ? ";
            statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            statement.setBoolean(1, t.isPresente());
            statement.setString(2, t.getEmail());
            statement.setInt(3, t.getId());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            String email=resultSet.getString(2);
            t = findByPrimaryKey(email,id);

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
            String delete = "DELETE FROM partecipante_evento " +
                    "WHERE partecipante_evento.email = ? " +
                    "AND partecipante_evento.id_evento = ?";
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setString(1, t.getEmail());
            statement.setInt(2, t.getId());
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
