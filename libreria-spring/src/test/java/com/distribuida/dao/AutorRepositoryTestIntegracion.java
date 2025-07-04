package com.distribuida.dao;
import com.distribuida.model.Autor;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.List;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class AutorRepositoryTestIntegracion {
    @Autowired
    private AutorRepository autorRepository;
    @Test
    public void findAll(){
        List<Autor> autores = autorRepository.findAll();
        for(Autor item:autores){
            System.out.println(item.toString());
        }
    }
}