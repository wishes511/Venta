/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Modelo.Cliente;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author GATEWAY1-
 */
public class mail {

    private Cliente c;
    private String cuerpo;
    private String subject;
    private String pedido;

    /**
     * Asignacion de valores para el correo
     *
     * @param cuerpo
     * @param subject titulo del correo
     * @param c
     * @param pedido
     */
    public mail(String cuerpo, String subject, Cliente c, String pedido) {
        this.c = c;
        this.subject = subject;
        this.cuerpo = cuerpo;
        this.pedido = pedido;
    }

    public void sendmail() {
        Properties props = new Properties();
//       ¨Propiedades para la conexion hacia Gmail, protocolos, puertos etc.
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
//        Estancia de sesion con Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
//                contraseña generada desde Gmail ya que directamente no era posible hacer el envio
//                Es importante esta parte ya que si no no se podra autenticar y hacer uso del mismo
                return new PasswordAuthentication("sistemas.athfootwear@gmail.com", "pkwdcvninprohutg");
            }
        });
        try {
//            Partes para rellenar un contenido
            MimeMultipart melementos = new MimeMultipart();
//            Contenido del correo
            MimeBodyPart cont = new MimeBodyPart();
            cont.setContent(cuerpo, "text/html; charset=utf-8");
            melementos.addBodyPart(cont);
//            Parte de la subida del archivo y añadirla al nodo padre
            MimeBodyPart archivo = new MimeBodyPart();
            archivo.setDataHandler(new DataHandler(new FileDataSource("c:\\af\\Venta\\pdf\\" + getpedformat(pedido) + ".PDF")));
            archivo.setFileName("pedido " + getpedformat(pedido) + ".pdf");
            melementos.addBodyPart(archivo);
//            Se agrega el remitente, asunto y el cuerpo del correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistemasathfootwear@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(c.getEmail()));
            message.setSubject(subject);
            message.setContent(melementos);
            //message.setText(cuerpo);
            Transport.send(message);
            System.out.println("Email sent.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getpedformat(String f) {
        String resp = "";
        for (int i = 0; i < f.length(); i++) {
            String aux = f.charAt(i) + "";
            if (!aux.equals(" ")) {
                resp += f.charAt(i) + "";
            }
        }
        return resp;
    }
}
