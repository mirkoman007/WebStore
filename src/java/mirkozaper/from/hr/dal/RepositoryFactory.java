/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.dal;

import mirkozaper.from.hr.dal.sql.SqlRepository;

/**
 *
 * @author mirko
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }
    
    public static Repository getRepository(){
        return new SqlRepository();
    }
}
