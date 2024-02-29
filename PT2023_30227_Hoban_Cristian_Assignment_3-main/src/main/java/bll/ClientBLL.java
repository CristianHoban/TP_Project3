package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;


public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());

        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
             throw new NoSuchElementException("The client with id =" + id + " was not found!");

        }
        return st;
    }

    public List<Client> findAll(){
        List<Client> c = new ArrayList<>();
        c = clientDAO.findAll();
        return c;
    }
    public Client insert(Client client) throws IllegalAccessException {
        Client c = clientDAO.insert(client);
        return c;
    }

    public Client delete(Client client, int i) throws IllegalAccessException {
        Client c = clientDAO.delete(i, client);
        return c;
    }
    public Client update(Client client) throws IllegalAccessException {
        Client c = clientDAO.update(client);
        return c;
    }


    /*public String[][] findAll(){
        List<Client> clients= clientDAO.findAll();
        int noOfFields = clientDAO.noOfFields();
        String[][] table = new String[clients.size()][noOfFields];
        int i = 0;
        for(Client e: clients){
            table[i][0] = String.valueOf(e.getId());
            table[i][1] = e.getName();
            table[i][2] = e.getAddress();
            table[i][3] = e.getEmail();
            table[i][4] = String.valueOf(e.getAge());
            i++;

        }
        return table;
    }*/

}
