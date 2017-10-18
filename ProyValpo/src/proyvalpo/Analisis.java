/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyvalpo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Mazhuka
 */
public class Analisis {

    protected ArrayList<Persona> electores=new ArrayList<Persona>();

    //private final String[] CerrosOriginal = {"Alegre", "Barón", "Blanco", "Bellavista", "Concepción", "Cordillera", "Delicias", "El Litre", "El Molino", "Esperanza", "Jiménez", "Larraín", "La Cruz", "La Cárcel", "La Florida", "La Merced", "La Virgen", "Las Cañas", "Las Jarcias", "Las Monjas", "Los Placeres", "Loceras", "Lecheros", "Mariposas", "Mesilla", "Miraflores", "O'Higgins", "Pajonal", "Panteón", "Playa Ancha", "Perdices", "Polanco", "Ramaditas", "Reina Victoria", "Rodelillo", "Rocuant", "San Juan de Dios", "Santo Domingo", "San Francisco", "Toro", "Yungay"};
    private final String[] cerro = {"Alegre", "Barón", "Blanco", "Bellavista", "Concepción", "Cordillera", "Delicias", "Litre", "Molino", "Esperanza", "Jiménez", "Larraín", "Cruz", "Cárcel", "Florida", "Merced", "Virgen", "Cañas", "Jarcias", "Monjas", "Placeres", "Loceras", "Lecheros", "Mariposas", "Mesilla", "Miraflores", "O'Higgins", "Pajonal", "Panteón", "Playa Ancha", "Perdices", "Polanco", "Ramaditas", "Reina Victoria", "Rodelillo", "Rocuant", "San_Juan_de_Dios", "Santo_Domingo", "San_Francisco", "Toro", "Yungay"};
    private ArrayList[] PersCerro = new ArrayList[cerro.length];
    private String nomArchivo;
    private XSSFWorkbook libroOut;
    private int lugar = 0;

    public  Analisis(String nom) throws IOException {
        nomArchivo = nom;
}

    public void SepararDatos() {
        
        while (!electores.isEmpty()) {
            String[] palabras = electores.get(lugar).getDomicilio().split(" ");
            boolean encontrado = false;
            for (int j = 0; j < palabras.length && encontrado; j++) {
                for (int k = 0; k < cerro.length && encontrado; k++) {
                    if (palabras[j].equalsIgnoreCase(cerro[k])) {
                        PersCerro[k].add(electores.get(lugar));
                        encontrado = true;
                        electores.remove(lugar);
                    }
                }
            }
            if (!encontrado) {
                lugar++;
            }
        }
    }

    public void CrearLibros() {
        for (int k = 0; k < cerro.length; k++) {
            EscribirLibro(PersCerro[k], cerro[k]);
        }
        crearLibro();
    }
        
    public void Lectura() throws IOException {
        FileInputStream file = new FileInputStream(nomArchivo);
        XSSFWorkbook libro = new XSSFWorkbook(file);
        XSSFSheet hoja = libro.getSheetAt(0);
        Iterator rows = hoja.rowIterator();
        int j = 0;
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            List<String> df = new ArrayList();
            for (int i = 0; i < 6; i++) {
                XSSFCell cell = null;
                if (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
                }
                df.add(agregarCelda(cell));
                

            }
            if (j != 0) {
                agregarDatoL(df);
            }
            j++;
        }
        file.close();
    }

    public void agregarDatoL(List<String> df) {
        Persona p = new Persona(df.get(0), df.get(1),df.get(2),df.get(3),df.get(4),df.get(5));

        electores.add(p);
        SepararDatos();
        
    }

    public String agregarCelda(XSSFCell cell) {
        if (cell == null) {
            return "";
        }
        return cell.toString();
    }
    
    public void EscribirLibro(ArrayList A,String nombreHoja){
        XSSFSheet hoja = libroOut.createSheet(nombreHoja);
        for (int i = 0; i < A.size(); i++) {
            XSSFRow fila = hoja.createRow(i);
            Persona p=(Persona)A.get(i);
            for (int j = 0; j < p.atts(); j++) {
                XSSFCell celda = fila.createCell(j);
                switch (j){
                    case 0 : celda.setCellValue(p.getNombre()); break;
                    case 1 : celda.setCellValue(p.getRUT()); break;
                    case 2 : celda.setCellValue(p.getSexo()); break;
                    case 3 : celda.setCellValue(p.getDomicilio()); break;
                    case 4 : celda.setCellValue(p.getCir()); break;
                    case 5 : celda.setCellValue(p.getMesa()); break;
                    default: celda.setCellValue(""); break;
                }
                hoja.autoSizeColumn(j);
            }
        }
    }
    
    public void crearLibro(){
         
        java.util.Date fecha = new Date();
        String[] fechaV = fecha.toString().split(" ");
        String[] fechaVH = fechaV[3].split(":");
        String nfecha = fechaV[2] + "_" + fechaV[1] + "_" + fechaV[5] + "_" + fechaVH[0] + "_" + fechaVH[1] + "_" + fechaVH[2];
        try {
            FileOutputStream elFichero = new FileOutputStream("Valpo_" + nfecha + ".xlsx");
            libroOut.write(elFichero);
            elFichero.close();
        } catch (Exception e) {
        }
    }
        
}
