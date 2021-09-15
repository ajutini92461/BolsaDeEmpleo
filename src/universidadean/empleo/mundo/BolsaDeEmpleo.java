/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnología de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Basado en un Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Bolsa de Empleo
 * Fecha: 11 de marzo de 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.empleo.mundo;

import java.util.ArrayList;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o más con el mismo nombre
 */
public class BolsaDeEmpleo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList<Aspirante> aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo() {
        aspirantes = new ArrayList<>();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     *
     * @return lista de aspirantes
     */
    public ArrayList<Aspirante> darAspirantes() {
        ArrayList<Aspirante> copia = new ArrayList<>(aspirantes);
        return copia;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     *
     * @param nombreA           El nombre del aspirante - nombreA != null
     * @param profesionA        La profesión del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA Años de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA             La edad del aspirante - edadA > 0
     * @param telefonoA         El teléfono del aspirante - telefonoA != null
     * @param imagenA           La ruta a la imagen del aspirante - imagenA != null
     * @return Se retornó true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante(String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA) {
        int aspiranteBuscado = buscarAspirante(nombreA);
        boolean agregado = false;
        if (aspiranteBuscado == -1) {
            Aspirante nuevoAspirante = new Aspirante(nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA);
            aspirantes.add(nuevoAspirante);
            agregado = true;
        }

        return agregado;
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes está ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre() {
        for(int i = 0; i < aspirantes.size(); ++i){
            for(int j = 0; j < aspirantes.size() - 1; ++j){
                if(aspirantes.get(j).darNombre().compareTo(aspirantes.get(j+1).darNombre())>0){
                    Aspirante aux = aspirantes.get(j);
                    aspirantes.set(j, aspirantes.get(j + 1));
                    aspirantes.set(j + 1, aux);
                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selección <br>
     * <b>post: </b>La lista de aspirantes está ordenada por edad
     */
    public void ordenarPorEdad() {
        for(int i = 0; i < aspirantes.size(); ++i) {
            for (int j = i + 1; j < aspirantes.size(); ++j) {
                if (aspirantes.get(j).darEdad() < aspirantes.get(i).darEdad()) {
                    Aspirante aux = aspirantes.get(i);
                    aspirantes.set(i, aspirantes.get(j));
                    aspirantes.set(j, aux);
                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por profesión usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesión
     */
    public void ordenarPorProfesion() {
        for(int i = 0; i < aspirantes.size(); ++i){
            for(int j = 0; j < aspirantes.size() - 1; ++j){
                if(aspirantes.get(j).darProfesion().compareTo(aspirantes.get(j+1).darProfesion())>0){
                    Aspirante aux = aspirantes.get(j);
                    aspirantes.set(j, aspirantes.get(j + 1));
                    aspirantes.set(j + 1, aux);
                }
            }
        }
    }

    /**
     * Organiza la lista de aspirantes por años de experiencia usando el algoritmo de inserción <br>
     * <b>post: </b>La lista de aspirantes está ordenada por años de experiencia
     */
    public void ordenarPorAniosDeExperiencia() {
        int i, j;
        for (i = 1; i < aspirantes.size(); i++){
            Aspirante aux = aspirantes.get(i);
            j = i - 1;
            while ((j >= 0) && (aux.darAniosExperiencia() > aspirantes.get(j).darAniosExperiencia())){
                aspirantes.set(j + 1,aspirantes.get(j));
                j--;
                aspirantes.set(j + 1,aux);
            }
        }
    }

    /**
     * Busca un Aspirante según su nombre y retorna la posición en la que se encuentra. <br>
     *
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retornó la posición donde se encuentra un aspirante con el nombre dado. Si no se encuentra ningún aspirante con ese nombre se retornó -1.
     */
    public int buscarAspirante(String nombre) {
        int posicion = -1;
        for(int i = 0; i < aspirantes.size(); ++i) {
            if(aspirantes.get(i).darNombre().indexOf(nombre) > -1 ){
                posicion = i;
            }
        }
        return posicion;
    }
    /**
     * Busca un aspirante utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     *
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retornó la posición del aspirante con el nombre dado. Si el aspirante no existe se retornó -1.
     */
    public int buscarBinarioPorNombre(String nombre) {
        int posicion = -1;
        int ini = 0;
        int fin = aspirantes.size() - 1;
        int mitad;
        while (ini <= fin) {
            mitad = (ini + fin) / 2;
            if (aspirantes.get(mitad).darNombre().compareTo(nombre) == mitad)
                return mitad;
            else if ( mitad < aspirantes.get(mitad).darNombre().compareTo(nombre)) {
                ini = mitad + 1;
            }
            else {
                fin = mitad - 1;
            }
        }
        return posicion;
    }


    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante más joven. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMasJoven() {
        int posicion = -1;
        int menor = Integer.MAX_VALUE;
        for(int i = 0; i < aspirantes.size(); ++i) {
            if(aspirantes.get(i).darEdad() < menor ){
                menor = aspirantes.get(i).darEdad();
                posicion = i;
            }
        }

        return posicion;
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante con más edad. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorEdad() {
        int posicion = -1;
        int mayor = Integer.MIN_VALUE;
        for(int i = 0; i < aspirantes.size(); ++i) {
            if(aspirantes.get(i).darEdad() > mayor){
                mayor = aspirantes.get(i).darEdad();
                posicion = i;
            }
        }

        return posicion;
    }

    /**
     * Busca el aspirante con más años de experiencia en la bolsa.
     *
     * @return Se retornó la posición donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorExperiencia() {
        int posicion = -1;
        int mayorE = Integer.MIN_VALUE;
        for(int i = 0; i < aspirantes.size(); ++i) {
            if(aspirantes.get(i).darAniosExperiencia() > mayorE){
                mayorE = aspirantes.get(i).darAniosExperiencia();
                posicion = i;
            }
        }
        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se eliminó el aspirante de la lista de aspirantes.
     *
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retornó true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante(String nombre) {
        boolean contratado = false;
        if(buscarBinarioPorNombre(nombre) > -1){
            aspirantes.remove(buscarBinarioPorNombre(nombre));
            return true;
        }
        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos años de experiencia <br>
     * son menores a la cantidad de años especificada <br>
     *
     * @param aniosExperiencia La cantidad de años con relación a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia(int aniosExperiencia) {
        int eliminados = 0;
        int cont = 0;
        for(int i = aspirantes.size()-1; i >= 0; --i){
            if(aspirantes.get(i).darAniosExperiencia() < aniosExperiencia){
                aspirantes.remove(aspirantes.get(i));
                cont += 1;
                eliminados = cont;
            }
        }
        return eliminados;
    }

}