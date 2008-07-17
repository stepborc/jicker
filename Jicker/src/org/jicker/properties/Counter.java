package org.jicker.properties;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.util.Properties;



import javax.swing.JButton;

import javax.swing.JFrame;


//http://www.wer-weiss-was.de/theme35/article4201693.html


public class Counter extends JFrame implements ActionListener{

JButton open;

Properties props;

Properties props2;

FileInputStream input;

FileOutputStream output;

int counter =  1;

String filename;



public Counter(){

JButton ink = new JButton();

JButton get = new JButton();

FlowLayout flow = new FlowLayout();



ink.setText("erhöhen");

get.setText("get");

ink.addActionListener(this);

get.addActionListener(this);

this.setSize(300, 300);

this.setLayout(flow);

this.add(ink);

this.add(get);

filename = "counter.properties";



Properties props = new Properties();



}



public void save(int i){

try{

output = new FileOutputStream(filename);

props.put("Counter", i);

props.store(output, "Die Props counter!");

}catch (Exception e){

e.printStackTrace();

}



}



public int load (){

try{

input = new FileInputStream(filename);

props.load(input);

counter = Integer.parseInt(props.get("Counter").toString());



}catch (Exception d){

d.printStackTrace();

} 

return counter;



}



public void actionPerformed(ActionEvent e){

String label = e.getActionCommand();



if (label.equals("erhöhen")){

counter++;

try{

if (new File(filename).exists()){

save(counter);

}

else  {

save(counter);

}

}catch (Exception d){

d.printStackTrace();

}



}





if (label.equals("get")){

counter = load();

System.out.println(counter);



}

}



public static void main (String[] args){

Counter count = new Counter();
count.setVisible(true);
//count.show();



}



}

