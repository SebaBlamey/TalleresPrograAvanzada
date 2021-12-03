import Clases.*;
import Herencia.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SistemaUCRImpl implements SistemaUCR{
    listaEstudiantes lEstudiantes;
    listaAsignaturas lAsignaturas;
    listaParalelo lParalelo;
    listaProfesores lProfesores;

    public SistemaUCRImpl(){
        lEstudiantes = new listaEstudiantes(1000);
        lAsignaturas = new listaAsignaturas(1000);
        lParalelo = new listaParalelo(1000);
        lProfesores = new listaProfesores(1000);
    }

    @Override
    public boolean ingresarEstudiante(String rut, String correo, int nivel, String contrasena) {
        Estudiante estudiante = new Estudiante(rut,correo,nivel,contrasena);
        lEstudiantes.anadirEstudiante(estudiante);
        return false;
    }

    @Override
    public boolean ingresarAsignaturasCursadas(String rutEstudiante, String codigo, Double notafinal) {
        Estudiante estudiante = lEstudiantes.buscarEstudiante(rutEstudiante);
        if(estudiante != null){
            AsignaturasCursadas ac = new AsignaturasCursadas(codigo,notafinal);
            estudiante.getlAcursadas().anadirAsignaturaC(ac);
        }
        return false;

    }

    @Override
    public boolean ingresarAsignaturasInscritas(String rutEstudiante, String codigoAsignaturaInscrita, String paralelo) {
        Estudiante estudiante = lEstudiantes.buscarEstudiante(rutEstudiante);
        if(estudiante != null){
            AsignaturasInscritas ai = new AsignaturasInscritas(codigoAsignaturaInscrita,paralelo);
            estudiante.getlAinscritas().anadirAsignaturaI(ai);
        }
        return false;
    }

    @Override
    public boolean ingresarAsignaturaOpcional(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int cantidadPreRequisitos) {
        AsignaturasOpcionales asignaturaOpcionales = new AsignaturasOpcionales(codigo,nombreAsignatura,cantCreditos,tipoAsignatura,cantidadPreRequisitos);
        lAsignaturas.anadirAsignatura(asignaturaOpcionales);
        return false;
    }

    @Override
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int nivel, int cantidadPreRequisitos, String codigos) {
        AsignaturaObligatorias asignaturaObligatorias = new AsignaturaObligatorias(codigo,nombreAsignatura,cantCreditos,tipoAsignatura,nivel,cantidadPreRequisitos,codigos);
        lAsignaturas.anadirAsignatura(asignaturaObligatorias);
        return false;
    }

    @Override
    public boolean ingresarProfesores(String rut, String correo, String contrasena, int salario) {
        Profesores profesor = new Profesores(rut,correo,contrasena,salario);
        lProfesores.anadirProfesor(profesor);
        return false;
    }

    @Override
    public boolean acociarParalelo(String paralelo, String codigo, String rutProfesor) {
        Paralelo paralelos = new Paralelo(paralelo);
        Asignaturas asignatura = lAsignaturas.buscarAsignatura(codigo);
        Profesores profesor = lProfesores.buscarProfesor(rutProfesor);
        if(asignatura != null && profesor != null){
            paralelos.setAsignaturas(asignatura);
            paralelos.setProfesor(profesor);
        }
        lParalelo.anadirParalelo(paralelos);
        return false;
    }

    @Override
    public int inicioSesion(String correo, String pass) {
        Estudiante estudiante = lEstudiantes.buscarEstudianteCorreo(correo);
        Profesores profesor = lProfesores.buscarProfesorCorreo(correo);
        try {
            if (correo.equals("Admin") && pass.equals("GHI_789")) {
                return 0;
            }
            if(estudiante != null){
                if (correo.equals(estudiante.getCorreo()) && pass.equals(estudiante.getContrasena())) {
                    return 1;
                }
            }
            if(profesor !=null){
                if (correo.equals(profesor.getCorreo()) && pass.equals(profesor.getContrasena())) {
                    return 2;
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    public String correoCompletoEstudiante(String correo){
        Estudiante e = lEstudiantes.buscarEstudianteCorreo(correo);
        return e.getCorreo();
    }

    public String rutCompletoProfesor(String correo){
        Profesores p = lProfesores.buscarProfesorCorreo(correo);
        return  p.getRut();
    }

    @Override
    public int periodosSemestre(Date fecha) {
        Date inicio1 = new Date(2021,3,8);
        Date inicio2 = new Date(2021,5,2);

        Date Mitad1 = new Date(2021,5,3);
        Date Mitad2 = new Date(2021,7,11);

        Date Final1 = new Date(2021, 7,12);
        Date Final2 = new Date(2021, 7,25);

        Date CierreSemestre = new Date(2021,7,26);
        if(fecha.after(inicio1) && fecha.before(inicio2)){
            return 0;
        }
        else if(fecha.after(Mitad1) && fecha.before(Mitad2)){
            return 1;
        }
        else if(fecha.after(Final1) && fecha.before(Final2)){
            return 2;
        }
        else if(fecha.equals(CierreSemestre)){
            return 3;
        }
        else{
            return -1;
        }
    }

    @Override
    public void DesplegarAsignaturas(String corre) {
        //String text = "";
        Estudiante e = lEstudiantes.buscarEstudianteCorreo(corre);
        String tipo = "";
        String cabecera1 = "Nombre";
        String cabecera2 = "Tipo";
        String cabecera3 = "Codigo";
        String cabecera4 = "Creditos";
        String nombre,codigo,creditos;
        int cont = 0;
        int nivelEstudiante = e.getNivel();
        System.out.printf("%-17s %14s %17s %17s %n",cabecera1,cabecera2,cabecera3,cabecera4);
        for(int i = 0; i<lAsignaturas.getCant();i++){
            Asignaturas a = lAsignaturas.getAsignaturasX(i);
            if(a.getTipoAsignatura().equalsIgnoreCase("obligatoria")){
                tipo = "Obligatoria";
                AsignaturaObligatorias aO = (AsignaturaObligatorias) a;
                if(aO.getNivel() == nivelEstudiante){
                    nombre = aO.getNombreAsignatura();
                    codigo = aO.getCodigoAsignatura();
                    if(!yaTieneAsignatura(corre,codigo)) {
                        creditos = String.valueOf(aO.getCreditoAsignatura());
                        System.out.printf("%-17s %14s %17s %17s %n", nombre, tipo, codigo, creditos);
                        cont++;
                    }
                }
            }
            if(a.getTipoAsignatura().equalsIgnoreCase("opcional")){
                tipo = "Opcional";
                AsignaturasOpcionales aOp = (AsignaturasOpcionales) a;
                if(e.getCredito()>=aOp.getCantidadPreRequisitos()){
                    nombre = aOp.getNombreAsignatura();
                    codigo = aOp.getCodigoAsignatura();
                    if(!yaTieneAsignatura(corre,codigo)) {
                        creditos = String.valueOf(aOp.getCreditoAsignatura());
                        System.out.printf("%-17s %14s %17s %17s %n", nombre, tipo, codigo, creditos);
                        cont++;
                    }
                }
            }
        }
        if(cont==0){
            System.out.println("No tienes Asignaturas disponibles para inscribir");
        }
    }
    private boolean yaTieneAsignatura(String correo, String codigoAsignatura){
        Estudiante e = lEstudiantes.buscarEstudianteCorreo(correo);
        boolean laTiene = false;
        AsignaturasInscritas ai = e.getlAinscritas().buscarAsignaturaI(codigoAsignatura);
        if(ai != null){
            laTiene = true;
        }
        return laTiene;
    }

    @Override
    public boolean codigoValido(String correo, String codigo) {
        boolean valido = false;
        Estudiante e = lEstudiantes.buscarEstudianteCorreo(correo);
        try {
            Asignaturas a = lAsignaturas.buscarAsignatura(codigo);
            if(a.getTipoAsignatura().equalsIgnoreCase("obligatoria")){
                AsignaturaObligatorias aO = (AsignaturaObligatorias) a;
                if(aO.getNivel() == e.getNivel() && (aO.getCreditoAsignatura()+e.getCredito() <= 40)){
                    valido = true;
                }

            }else if (a.getTipoAsignatura().equalsIgnoreCase("opcional")){
                AsignaturasOpcionales aOp = (AsignaturasOpcionales) a;
                if(e.getCredito()>=aOp.getCantidadPreRequisitos() && (aOp.getCreditoAsignatura()+e.getCredito() <= 40)){
                    valido = true;
                }
            }


        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("El codigo ingresado no es valido");
            valido = false;
        }
        System.out.println(valido+" 1");
        return valido;
    }

    @Override
    public void DesplegarParalelos(String correo, String codigo) {
        String cabecera1 = "Numero";
        String cabecera2 = "Codigo";
        String cabecera3 = "Profesor";
        String cabecera4 = "Rut";
        String numero, profesor,rut;
        System.out.printf("%-9s %11s %27s %18s %n", cabecera1, cabecera2, cabecera3, cabecera4);
        for(int i = 0; i < lParalelo.getCant();i++){
            try {
                Paralelo p = lParalelo.getParaleloX(i);
                if(p.getAsignaturas().getCodigoAsignatura().equals(codigo)) {
                    numero = p.getNumeroParalelo();
                    profesor = p.getProfesor().getCorreo();
                    rut = p.getProfesor().getRut();
                    System.out.printf("%-9s %11s %27s %18s %n", numero, codigo, profesor, rut);
                }
            }catch (Exception e){

            }
        }
    }

    @Override
    public boolean InscribirParalelo(String correo, String codigo, int numeroParalelo) {
        boolean inscrito = false;
        for(int i = 0 ; i < lParalelo.getCant() ; i ++){
            Paralelo p = lParalelo.getParaleloX(i);
            if(p.getAsignaturas().getCodigoAsignatura().equals(codigo) &&
                    p.getNumeroParalelo().equals(String.valueOf(numeroParalelo))){
                    Estudiante e = lEstudiantes.buscarEstudianteCorreo(correo);
                    AsignaturasInscritas a = new AsignaturasInscritas(codigo,String.valueOf(numeroParalelo));
                    e.getlAinscritas().anadirAsignaturaI(a);
                    int numeroAlumnos = p.getCantAlumnos()+1;
                    p.setCantAlumnos(numeroAlumnos);
                    int creditos = e.getCredito()+p.getAsignaturas().getCreditoAsignatura();
                    e.setCredito(creditos);
                    inscrito =true;
            }
        }
        return inscrito;
    }

    @Override
    public String asignaturasInscritas(String correo) {
        String texto = "";
        String nombreA, tipoAsignatura, codigo, paralelo;
        Estudiante e = lEstudiantes.buscarEstudianteCorreo(correo);
        if(e.getlAinscritas().getCant() > 0) {
            for (int i = 0; i < e.getlAinscritas().getCant(); i++) {
                AsignaturasInscritas ai = e.getlAinscritas().getAsignaturasIX(i);
                codigo = ai.getCodigo();
                paralelo = ai.getCodigoParalelo();
                Paralelo p = lParalelo.buscarParaleloCodigo(codigo);
                nombreA = p.getAsignaturas().getNombreAsignatura();
                tipoAsignatura = p.getAsignaturas().getTipoAsignatura();
                texto += ("Nombre Asignatura: " + nombreA + "\nTipo Asignatura: " + tipoAsignatura +
                        "\nCodigo: " + codigo + "\nParalelo: " + paralelo);
                if (i + 1 != e.getlAinscritas().getCant()) {
                    texto += "\n---------------------------------------\n";
                }
            }
        }
        else if( e.getlAinscritas().getCant() == 0){
            texto = "No tienes asignaturas inscritas";
        }
        return texto;
    }

    @Override
    public boolean eliminarAsignatura(String correo, String codigo) {
        boolean eliminado = false;
        Estudiante e = lEstudiantes.buscarEstudianteCorreo(correo);
        AsignaturasInscritas ai = e.getlAinscritas().buscarAsignaturaI(codigo);
        if(ai != null){
            Asignaturas a = lAsignaturas.buscarAsignatura(codigo);
            e.getlAinscritas().borrarAsignaturaI(codigo);
            int creditos = e.getCredito() - a.getCreditoAsignatura();
            e.setCredito(creditos);
            eliminado = true;
        }

        return eliminado;
    }

    @Override
    public String desplegarParalelosProfesor(String rut) {
        String texto = "";
        int cont = 0;
        Profesores p = lProfesores.buscarProfesor(rut);
        for(int i = 0; i < lParalelo.getCant();i++){
            Paralelo pa = lParalelo.getParaleloX(i);
            if(pa.getProfesor().getRut().equals(p.getRut())){
                texto += ("Asignatura: "+pa.getAsignaturas().getNombreAsignatura()+"\nParalelo: "+pa.getNumeroParalelo()
                        +"\nCodigo: "+pa.getAsignaturas().getCodigoAsignatura());
                cont++;
                if(i+1 != lParalelo.getCant()){
                    texto+= "\n---------------------------------------\n";
                }
            }
        }
        if(cont == 0){
            texto = "No tiene paralelos registrados";
        }
        return texto;
    }

    @Override
    public String desplegarChequeoAlumnos(String numeroParalelo, String codigoAsignatura) {
        String texto = "";
        int cont = 0;
        for(int i = 0 ; i < lEstudiantes.getCant() ;i ++){
            Estudiante e = lEstudiantes.getEstudianteX(i);
            AsignaturasInscritas ai = e.getlAinscritas().buscarAsignaturaI(codigoAsignatura);
            if(ai != null){
                if(ai.getCodigoParalelo().equals(numeroParalelo)){
                    texto += ("Correo: "+e.getCorreo()+"\nRut: "+e.getRut());
                    if(cont != lEstudiantes.getCant()){
                        texto+= "\n---------------------------------------\n";
                    }
                    cont++;
                }
            }
        }
        if(cont == 0){
            texto = "No tiene alumnos en este paralelo";
        }
        return texto;
    }

    @Override
    public boolean ingresarNotaFinal(String rutAlumno, double notaFinal, String numeroParalelo, String codigoAsignatura) {
        boolean notaIngresada = false;
        Estudiante e = lEstudiantes.buscarEstudiante(rutAlumno);
        if(e != null){
            AsignaturasCursadas ac = new AsignaturasCursadas(codigoAsignatura,notaFinal);
            e.getlAcursadas().anadirAsignaturaC(ac);
            e.getlAinscritas().borrarAsignaturaI(codigoAsignatura);
            notaIngresada = true;
        }
        return notaIngresada;
    }

    @Override
    public int ConsolidarSistema() throws IOException {
        int cont = 0;
        String rut ="";
        for(int i = 0; i< lEstudiantes.getCant(); i++){
            Estudiante e = lEstudiantes.getEstudianteX(i);
            if(e.getNivel()==10){
                rut = e.getRut()+"\n";
                lEstudiantes.borrarEstudiante(rut);
                cont++;
            }
        }
        PrintWriter file = new PrintWriter("EstudiantesEgresados.txt","UTF-8");
        file.println(rut);
        file.close();
        return cont;
    }
}
