package dao;

import model.Client;

public class ClientDAO extends AbstractDAO<Client> {

    // uses basic CRUD methods from superclass

    // TODO: create only student specific queries

    public StringBuilder[] elements(Client c){
        StringBuilder[] e = new StringBuilder[noOfFields()];
        e[0] = new StringBuilder(String.valueOf(c.getId()));
        e[1] = new StringBuilder(String.valueOf(c.getName()));
        e[2] = new StringBuilder(String.valueOf(c.getAddress()));
        e[3] = new StringBuilder(String.valueOf(c.getEmail()));
        e[4] = new StringBuilder(String.valueOf(c.getAge()));

        return e;
    }
}
