/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.livro.Livro.controle;

import br.edu.ifrs.livro.Livro.dao.LivrosDao;
import br.edu.ifrs.livro.Livro.modelo.Livro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author mathe
 */
public class Livros {
    
    @Autowired
    LivrosDao livrosDao;
    
    @RequestMapping(path= "/livros/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Livro> listar(){
        return livrosDao.findAll();
    }
    
    
    @RequestMapping(path="/livros/{id}", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Livro recuperar (@PathVariable int id) {
        Optional<Livro> optLivro = livrosDao.findById(id);
    
        if(optLivro.isPresent()){
            return optLivro.get();
        }
        else {   
            return null;
        }
    }
    /*
    
    versão usando o entity
    @RequestMapping(path="/livros/{id}", method =RequestMethod.GET)
    public ResponseEntity<Livro> recuperar (@PathVariable int id) {
        Optional<Livro> optLivro = livrosDao.findById(id);
    
        if(optLivro.isPresent()){
            return ResponseEntity.ok(optLivro.get());
        }
        else {   
            return ResponseEntity.notFound().build();
        }
    }
    */
    
    
    @RequestMapping(path= "/livros/", method = RequestMethod.POST)
    public Livro insere(@RequestBody Livro livro){
        livro.setId(0);
        livrosDao.save(livro);
        return livro; 
        
        //Metodo Antigo!
        /*
        livro.setId(cont);
        cont++;
        livros.add(livro);
        */
       
    }
    
    @RequestMapping(path= "/livros/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable int id){
        livrosDao.deleteById(id);

}
    
    @RequestMapping(path= "/livros/{id}", method = RequestMethod.PUT)
    public void atualiza(@PathVariable int id, @RequestBody Livro livroNovo){
        //produtoDAO.save(produtoNovo);
        Livro livroAntigo = this.recuperar(id);
        livroAntigo.setAutorPrimeiroNome(livroNovo.getAutorPrimeiroNome());
        livroAntigo.setAutorSegundoNome(livroNovo.getAutorSegundoNome());
        livroAntigo.setAnoPublicacao(livroNovo.getAnoPublicacao());
        livroAntigo.setEditora(livroNovo.getEditora());
        
    }
}
