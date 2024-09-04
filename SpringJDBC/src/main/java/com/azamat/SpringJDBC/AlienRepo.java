package com.azamat.SpringJDBC;

import com.azamat.SpringJDBC.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlienRepo {

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
    }

    public void save(Alien alien){

        String sql = "insert into alien ( id, name, tech) values (?,?,?)";
        int rows = template.update(sql, alien.getId(), alien.getName(), alien.getTech());
        System.out.println(rows + " rows affected");
    }

    public List<Alien> findAll(){

         String sql = " select * from alien";

         List<Alien> aliens = template.query(sql, (rs, row) -> {
             Alien a = new Alien();
             a.setId(rs.getInt(1));
             a.setName(rs.getString(2));
             a.setTech(rs.getString(3));
             return a;

         });
         return aliens;

    }


}
