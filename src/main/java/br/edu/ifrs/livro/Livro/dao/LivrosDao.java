/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.livro.Livro.dao;

import br.edu.ifrs.livro.Livro.modelo.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface LivrosDao extends CrudRepository<Livro, Integer> {
    
}

