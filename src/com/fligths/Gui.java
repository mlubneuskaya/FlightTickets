package com.fligths;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.util.List;
import java.util.Vector;

public class Gui extends JFrame {

    private static Font FONT = new Font("", Font.BOLD, 20);
    private static Font SMALL_FONT = new Font("", Font.BOLD, 15);

    private FlightCommandExecutor executor;

    public static void main(String[] args) {
        FlightTicketSearch ticket = new FlightTicketSearchClient();
        Gui gui = new Gui(ticket);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Gui(FlightTicketSearch ticket) {
        this.executor = new FlightCommandExecutor(ticket);

        setSize(2000, 2000);
        setLayout(null);

        JLabel departureLabel = make_label(20, 65, 400, 12, "from");

        String initialItem = "Minsk";

        JComboBox<String> departureBox = make_ComboBox(20, 80, 400, 60, initialItem,
                ticket.departure_list(""));

        JComboBox<String> destinationBox = make_ComboBox(440, 80, 400, 60, "",
                ticket.destination_list(initialItem));

        JLabel destinationLabel = make_label(440, 65, 400, 12, "to");

        departureBox.addItemListener(e -> {
            List<String> destinations = ticket.destination_list(departureBox.getSelectedItem().toString());
            destinationBox.setModel(new DefaultComboBoxModel<>(destinations.toArray(new String[0])));
        });

        JTextArea Results = new JTextArea();
        Results.setVisible(false);
        Results.setFont(FONT);
        Results.setBounds(20, 160, 1200, 300);

        JButton cheapest = make_button(1000, 80, 200, 60, "cheapest tickets");

        cheapest.addActionListener(e -> {
            FlightCommand command = new FlightCommand("find_cheapest_ticket",
                    new String[]{departureBox.getSelectedItem().toString()});
            Results.append(executor.executeCommand(command));
            destinationBox.setSelectedItem(destinationBox.getItemAt(0));
            Results.setVisible(true);
        });

        JButton search = make_button(860, 80, 120, 60, "search");
        search.addActionListener(e -> {
            FlightCommand command = new FlightCommand("find_ticket",
                    new String[]{departureBox.getSelectedItem().toString(), destinationBox.getSelectedItem().toString()});
            Results.append(executor.executeCommand(command));
            Results.setVisible(true);
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

    private JLabel make_label(int x, int y, int w, int h, String name) {
        JLabel label = new JLabel(name);
        label.setBounds(x, y, w, h);
        label.setFont(SMALL_FONT);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JComboBox<String> make_ComboBox(int x, int y, int w, int h, String item, List<String> list) {
        JComboBox<String> box = new JComboBox<>(new Vector<>(list));
        box.setSelectedItem(item);
        box.setBounds(x, y, w, h);
        box.setFont(FONT);
        box.setEditable(true);
        return box;
    }

    ;

    private JButton make_button(int x, int y, int w, int h, String name) {
        JButton button = new JButton(name);
        button.setBackground(Color.ORANGE);
        button.setFont(FONT);
        button.setBounds(x, y, w, h);
        return button;
    }
}


