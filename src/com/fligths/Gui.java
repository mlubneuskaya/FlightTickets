package com.fligths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.*;
import java.util.ArrayList;

public class Gui extends JFrame {
    private JTextArea Results = new JTextArea();
    private JButton search = new JButton("search");
    private JButton cheapest = new JButton("cheapest tickets");
    private Font font = new Font("", Font.BOLD, 20);
    private Font smallFont = new Font("", Font.BOLD, 15);
    private JComboBox destinationBox;
    private JComboBox departureBox;
    private ArrayList<String> temp1 = new ArrayList<>();
    private ArrayList<String> temp2 = new ArrayList<>();

    public static void main(String[] args){
        Gui gui = new Gui();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlightTicket.print();
    }
    Gui(){
        setSize(2000, 2000);
        setLayout(null);
        JLabel departureLabel = make_label(20, 65, 400, 12, "from");
        temp1 = FlightTicket.departure_list("");
        Object[] departures = temp1.toArray();
        departureBox = new JComboBox(departures);
        departureBox.setSelectedItem("Minsk");
        departureBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                temp2 = FlightTicket.destination_list(departureBox.getSelectedItem().toString());
                temp2.add(0, "");
                destinationBox.setModel(new DefaultComboBoxModel(temp2.toArray()));
            }
        });
        departureBox.setBounds(20, 80, 400, 60);
        departureBox.setFont(font);
        departureBox.setEditable(true);
        //destination
        temp2 = FlightTicket.destination_list(departureBox.getSelectedItem().toString());
        temp2.add(0, "");
        Object[] destinations = temp2.toArray();
        destinationBox = new JComboBox(destinations);
        destinationBox.setBounds(440, 80, 400, 60);
        destinationBox.setFont(font);
        destinationBox.setEditable(true);
        JLabel destinationLabel = make_label(440, 65, 400, 12, "to");
        //Result field
        Results.setVisible(false);
        Results.setFont(font);
        Results.setBounds(20, 160, 1200, 300);
        //cheap button
        cheapest.setBounds(1000, 80, 200, 60);
        cheapest.setBackground(Color.ORANGE);
        cheapest.setFont(font);
        cheapest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results.append(FlightTicket.find_cheapest(departureBox.getSelectedItem().toString()).toString());
                destinationBox.setSelectedItem(destinationBox.getItemAt(0));
                Results.setVisible(true);
            }
        });
        //search button
        search.setBounds(860, 80, 120, 60);
        search.setBackground(Color.ORANGE);
        search.setFont(font);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results.append(FlightTicket.ticket_search(departureBox.getSelectedItem().toString(),
                        destinationBox.getSelectedItem().toString()));
                Results.setVisible(true);
                //bar.setVisible(true);
            }
        });
        //everything
        getContentPane().setBackground(Color.BLUE);
        getContentPane().add(search);
        getContentPane().add(cheapest);
        getContentPane().add(departureBox);
        getContentPane().add(departureLabel);
        getContentPane().add(destinationBox);
        getContentPane().add(destinationLabel);
        getContentPane().add(Results);
    }
    private JLabel make_label(int x, int y, int w, int h, String name){
        JLabel label = new JLabel(name);
        label.setBounds(x, y, w, h);
        label.setFont(smallFont);
        label.setForeground(Color.WHITE);
        return label;
    }
}


