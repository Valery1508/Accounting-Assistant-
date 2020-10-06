package sample.controller;

import sample.Repository.CustomerRepository;
import sample.Repository.DeliveryNoteRepository;
import sample.model.Customer;
import sample.model.DeliveryNote;

import java.sql.SQLException;
import java.util.List;

public class DeliveryNoteController {
    private DeliveryNote deliveryNote;
    private List<Integer> idList;

    public void addDeliveryNoteToBD(int idC, int codeP, String date, Double price, int quantity) throws SQLException {
        deliveryNote = new DeliveryNote(idC, codeP, date, price, quantity);
        DeliveryNoteRepository deliveryNoteRepository = new DeliveryNoteRepository();
        deliveryNoteRepository.addDN(deliveryNote);
    }

    public List<Integer> getIdList() throws SQLException {
        DeliveryNoteRepository deliveryNoteRepository = new DeliveryNoteRepository();
        idList = deliveryNoteRepository.getIds();
        return idList;
    }

    public void deleteDNFromDB(int id) throws SQLException {
        DeliveryNoteRepository deliveryNoteRepository = new DeliveryNoteRepository();
        deliveryNoteRepository.deleteDN(id);
    }

}
