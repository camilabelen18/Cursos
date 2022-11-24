package servicios;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;

import modelo.Usuario;


public class ServicioMercadoPago {

    public Preference checkout(Usuario usuario, Double precio){
        Preference preferencia = new Preference();
        try{
            MercadoPago.SDK.setAccessToken("TEST-6010487678159604-111900-ac78cd1e59ae97fc6ceecd0353a62d56-168040680");
            Preference p = new Preference();
            Item item = new Item();
            item.setTitle("Comprar curso").setQuantity(1).setUnitPrice(precio.floatValue());
            p.appendItem(item);
            Payer payer = new Payer();
            payer.setEmail(usuario.getEmail());
            p.setPayer(payer);
            p.setBinaryMode(true);
            preferencia = p.save();
        }catch (MPException e){
            System.out.println("Exeception MP\n");
            e.printStackTrace();
        }

        return preferencia;
    }
}