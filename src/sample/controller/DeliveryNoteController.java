package sample.controller;

import javafx.scene.control.TableView;
import sample.Repository.CustomerRepository;
import sample.Repository.DeliveryNoteRepository;
import sample.model.Customer;
import sample.model.DeliveryNote;
import sample.view.deliveryNote.EditDeliveryNoteRecordFrame;
import sample.view.table.DeliveryNoteTable;


import java.sql.SQLException;
import java.util.List;

public class DeliveryNoteController {
    private DeliveryNote deliveryNote;
    private List<Integer> idList;
    private DeliveryNoteTable deliveryNoteTable;
    private List<DeliveryNote> listOfDNs;

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

    public List<DeliveryNote> getDNList() throws SQLException {
        DeliveryNoteRepository deliveryNoteRepository = new DeliveryNoteRepository();
        listOfDNs = deliveryNoteRepository.getAllDN();
        return listOfDNs;
    }

    public TableView<DeliveryNote> createTableOfDN() throws SQLException {
        deliveryNoteTable = new DeliveryNoteTable();
        return deliveryNoteTable.createTable(getDNList());
    }

    public void takeRecord() throws SQLException, ClassNotFoundException {
        int selectedId = deliveryNoteTable.getSelectedId();
        int selectedIdCustomer = deliveryNoteTable.getSelectedIdCustomer();
        int selectedCodeP = deliveryNoteTable.getSelectedCodeP();
        String selectedDate = deliveryNoteTable.getSelectedDate();
        Double selectedPrice = deliveryNoteTable.getSelectedPrice();
        int selectedQuantity = deliveryNoteTable.getSelectedQuantity();

        EditDeliveryNoteRecordFrame editDeliveryNoteRecordFrame = new EditDeliveryNoteRecordFrame();
        editDeliveryNoteRecordFrame.editDNRecord(selectedId, selectedIdCustomer, selectedCodeP, selectedDate, selectedPrice, selectedQuantity);
    }

    public void setEditedRecord(int oldId, int id, int idC, int codeP, String date, Double price, int quantity) throws SQLException {
        deleteDNFromDB(oldId);
        addDeliveryNoteToBD(idC, codeP, date, price, quantity);
    }


}
