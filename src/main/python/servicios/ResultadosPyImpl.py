import numpy as np


print("Ejecución desde Python")
print("###############################################################")

print("PACIENTES:")
for paciente in todosPacientes:
    print("Paciente: "+paciente.getNombre()+" ("+ str(paciente.getId()) +"):")
    print("   Farmacos:")
    for farmaco in paciente.getFarmacosPacientesList():
        print(" -"+farmaco.getNombre()+" (" +str(farmaco.getId()) + ")")


print("LABORATORIOS:")
for laboratorio in todosLaboratorios:
    print("Laboratorio: "+laboratorio.getNombre()+" ("+ str(laboratorio.getId()) +"):")
    print("   Farmacos:")
    for farmaco in laboratorio.getFarmacosLaboratoriosList():
        print(" -"+farmaco.getNombre()+" (" +str(farmaco.getId()) + ")")
