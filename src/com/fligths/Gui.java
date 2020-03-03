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
    private Font font = new Font("", Font.BOLD, 20);
    private Font smallFont = new Font("", Font.BOLD, 15);
    private JComboBox destinationBox;

    public static void main(String[] args){
        Gui gui = new Gui();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlightTicket.print();
    }


    private Gui(){
        setSize(2000, 2000);
        setLayout(null);
        JLabel departureLabel = make_label(20, 65, 400, 12, "from");
        JComboBox departureBox = make_ComboBox(20, 80, 400, 60, "Minsk",
                FlightTicket.departure_list(""));
        destinationBox = make_ComboBox(440, 80, 400, 60, "",
                FlightTicket.destination_list(departureBox.getSelectedItem().toString()));
        JLabel destinationLabel = make_label(440, 65, 400, 12, "to");
        departureBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                ArrayList temp = FlightTicket.destination_list(departureBox.getSelectedItem().toString());
                destinationBox.setModel(new DefaultComboBoxModel(temp.toArray()));
            }
        });
        //Result field
        Results.setVisible(false);
        Results.setFont(font);
        Results.setBounds(20, 160, 1200, 300);

        JButton cheapest = make_button(1000, 80,200, 60, "cheapest tickets");
        cheapest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results.append(FlightTicket.find_cheapest(departureBox.getSelectedItem().toString()).toString());
                destinationBox.setSelectedItem(destinationBox.getItemAt(0));
                Results.setVisible(true);
            }
        });

        JButton search = make_button(860, 80, 120 ,60, "search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Results.append(FlightTicket.ticket_search(departureBox.getSelectedItem().toString(),
                        destinationBox.getSelectedItem().toString()));
                Results.setVisible(true);
                //bar.setVisible(true);
            }
        });

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

    private JComboBox make_ComboBox(int x, int y, int w, int h, String item, ArrayList<String> list){
        Object[] countries = list.toArray();
        JComboBox box = new JComboBox(countries);
        box.setSelectedItem(item);
        box.setBounds(x, y, w, h);
        box.setFont(font);
        box.setEditable(true);
        return box;
    };

    private JButton make_button(int x, int y, int w, int h, String name){
        JButton button = new JButton(name);
        button.setBackground(Color.ORANGE);
        button.setFont(font);
        button.setBounds(x, y, w, h);
        return button;
    };
}


