import numpy as np


#Clase interoperable Java-Python que se importa en Python para poder devolver el resultado como un objeto
# de esta clase Java
from java.util import ArrayList


################ FUNCIONES ####################
#La función "generaArray" recibe los nombres de los fármacos del laboraotorio y del paciente. Los compara
# y genera el array booleano con los elementos True o False en función de las coincidencias de los fármacos
# en ambas listas
def generaArray(listaFarmacosPaciente, listaFarmacosLaboratorio):
    return np.array(list(map(lambda farmaco: farmaco in listaFarmacosPaciente, listaFarmacosLaboratorio)))


#La función "generalListaNombreFarmacosPaciente" genera un array con los nombres de los fármacos del paciente
def generalListaNombreFarmacosPaciente(elementoPaciente):
    listaNombreFarmacosPaciente=[]
    for farmaco in elementoPaciente.getFarmacosPacientesList():
        listaNombreFarmacosPaciente.append(farmaco.getNombre())
    return listaNombreFarmacosPaciente
####################################################



################# CUERPO DEL PROGRAMA #########################

#Genera el array de '1' con tantos elementos como fármacos tenga el laboratorio
laboratorio_vector = np.array(list(map(lambda farmaco: 1, laboratorio.getFarmacosLaboratoriosList())))

#Genera un array con los nombres de los fármacos del laboratorio
listaNombreFarmacosLaboratorio=[]
for farmaco in laboratorio.getFarmacosLaboratoriosList():
    listaNombreFarmacosLaboratorio.append(farmaco.getNombre())


#Recorre todos los pacientes y genera un mapa del tipo:
#     paciente : []
# donde "paciente" es un paciente concreto y [] es un array booleano, que va a tener valores True/False
# Tendrá tantos elementos como medicamentos tenga el laboratorio y sus valores estarán a True o False
# si el medicamento del laboratorio está dentro de los medicamentos del paciente. Si si está, el valor de
# la celda será True y si no lo está el valor será False.
#La función "generalListaNombreFarmacosPaciente" genera un array con los nombres de los fármacos del paciente
#La función "generaArray" recibe los nombres de los fármacos del laboraotorio y del paciente. Los compara
# y genera el array booleano que se comentaba antes
pacientes_vectores = {
    paciente.getNombre(): generaArray(generalListaNombreFarmacosPaciente(paciente), listaNombreFarmacosLaboratorio)
    for paciente in todosPacientes
}


#Array que va a almacenar el listado de pacientes que consumen algún fármaco de este laboratorio
#Inicialmente está vacío. Éste será el array que se devuelva
pacientesEncontrados = ArrayList()


#Recorriendo el mapa generado antes, se recorre generando otro array resultado del OR-EXCLUSIVO
# del vector de '1' de medicamentos del laboratorio y del vector booleano de cada paciente que indicaba
# en cada celda si el medicamento del paciente está en la lista de los medicamentos del laboratorio o no
# Los pacientes que tras este recorrido tengan un '0' en el array generado, formarán parte del listado
# de "pacientes encontrados" y pasarán a formar parte del array "pacientesEncontrados=[]"
for datosPaciente, arrayBooleanoFarmacos in pacientes_vectores.items():
    for elemento in (laboratorio_vector - arrayBooleanoFarmacos):
        if elemento == 0:
            pacientesEncontrados.add(datosPaciente)
