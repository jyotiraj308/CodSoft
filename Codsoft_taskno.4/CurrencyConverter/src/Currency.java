import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
public class Currency{
    JFrame frame1;
    String[] currencyName = {
            "AED(United Arab Emirates Dirham)",
            "ARS(Argentine Peso)",
            "AUD(Australina Dollar)",
            "BRL(Brazilian Real)",
            "CAD(Canadian Dollar)",
            "CHF(Swiss Franc)",
            "CLP(Chilean Peso)",
            "CNY(Chinese Yuan)",
            "COP(Colombian Peso)",
            "CZK(Czech Koruna)",
            "DKK(Danish Krone)",
            "EGP(Egyptian Pound)",
            "EUR(Euro)",
            "GBP(British Pound Sterling)",
            "HKD(Hong Kong Dollar)",
            "HRK(Croatian Kuna)",
            "HUF(Hungarian Forint)",
            "IDR(Indonesian Rupiah)",
            "ILS(Israeli New Shekel)",
            "INR(Inidan Rupee)",
            "ISK(Icelandic Krona)",
            "JPY(Japanese Yen)",
            "KRW(South Korean Won)",
            "MXN(Mexican Peso)",
            "MYR(Malaysian Ringgit)",
            "NOK(Norwegian Krone)",
            "NZD(New Zealand Dollar)",
            "PEN(Peruvian Sol)",
            "PHP(Philippine Peso)",
            "PLN(Polish Zoty)",
            "RON(Romanian Leu)",
            "RUB(Russian Ruble)",
            "SEK(Swedish Krona)",
            "SGD(Singapore Dollar)",
            "THB(Thai Baht)",
            "TRY(Turkish Lira)",
            "TWD(Taiwan New Dollar)",
            "UAH(Ukrainian Hryvnia)",
            "USD(United States Dollar)",
            "UYU(Uruguayan Peso)",
            "VND(Vietnamese Dong)",
            "ZAR(South African Rand)"
    };
    public void firstPage(){
        frame1 = new JFrame("Currency Converter");
        frame1.setBounds(400,200,600,300);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        JComboBox src = new JComboBox<>(currencyName);
        src.setBounds(250,20,280,30);
        src.setFont(new Font("Arial",Font.BOLD,16));
        JLabel label1 = new JLabel("Source Currency");
        label1.setBounds(50,20,190,30);
        label1.setFont(new Font("Arial",Font.BOLD,22));
        frame1.add(label1);
        frame1.add(src);
        JComboBox dest = new JComboBox<>(currencyName);
        dest.setBounds(250,170,280,30);
        dest.setFont(new Font("Arial",Font.BOLD,16));
        JLabel label2 = new JLabel("Target Currency");
        label2.setBounds(50,170,180,30);
        label2.setFont(new Font("Arial",Font.BOLD,22));
        frame1.add(label2);
        frame1.add(dest);
        JLabel amountLabel = new JLabel("Enter The Amount");
        amountLabel.setBounds(180,80,220,30);
        amountLabel.setFont(new Font("Arial",Font.BOLD,22));
        frame1.add(amountLabel);
        JTextField amount = new JTextField();
        amount.setBounds(210,110,120,30);
        amount.setFont(new Font("Arial",Font.BOLD,20));
        frame1.add(amount);
        JLabel showConversion = new JLabel("");
        showConversion.setBounds(110,210,350,35);
        showConversion.setFont(new Font("Arial",Font.BOLD,22));
        frame1.add(showConversion);
        JButton convert = new JButton("Convert");
        convert.setBounds(400,210,130,35);
        convert.setFont(new Font("Arial",Font.ITALIC,20));
        frame1.add(convert);
        src.setSelectedIndex(19);
        dest.setSelectedIndex(38);
        convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(amount.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(frame1,"Enter the amount","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(!(amount.getText().trim().matches("^[0-9.]+[0-9]+$"))){
                    JOptionPane.showMessageDialog(frame1,"Enter amount in correct format","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Float givenAmount = Float.parseFloat(amount.getText());
                String source = src.getSelectedItem().toString();
                String target = dest.getSelectedItem().toString();
                source = source.substring(0,3);
                target = target.substring(0, 3);
                String str = "";
                if(source.equals(target)){
                    str = amount.getText()+" "+source+" = "+amount.getText()+" "+target;
                }
                else{
                    float convertedValue = getConvertedValue(source, target, givenAmount);
                    if(convertedValue<0) {
                        JOptionPane.showMessageDialog(frame1, "Data is not fetched\nYou Should Check your Internet Connection", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String convertToString = String.valueOf(convertedValue);
                    str = amount.getText()+" "+source+" = "+convertToString+" "+target;
                }
                showConversion.setText(str);
            }
        });
        frame1.setVisible(true);
    }
    public float getConvertedValue(String source,String target,float amt){
        float targetAmount = 0;
        try{
            String GET_URL = "https://open.er-api.com/v6/latest/"+target;
            URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while((inputLine = in.readLine())!=null){
                    response.append(inputLine);
                }in.close();
                JSONObject obj = new JSONObject(response.toString());
                Double exchangeRate = obj.getJSONObject("rates").getDouble(source);
                targetAmount = (float)(amt/exchangeRate);
                return targetAmount;
            }
            else{
                return (float)-1;
            }
        }
        catch(Exception e){
//            System.out.println("Error is "+e);
//            JOptionPane.showMessageDialog(frame1,"Url not Fetched\nPlease check Your Internet Connection","Warning",JOptionPane.WARNING_MESSAGE);
            return (float)-1;
        }
    }
}

