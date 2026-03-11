package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.habilidades.Habilidad;

import org.example.movimientos.CalmMind;
import org.example.movimientos.Movimiento;


public class Main {
    static void main() {
    //Rotom
    ArrayList<Tipo> tiposRotom = new ArrayList<>();
    tiposRotom.add(Tipo.ELÉCTRICO);
    tiposRotom.add(Tipo.AGUA); 
    ArrayList<Movimiento> movimientosRotom = new ArrayList<>();
    movimientosRotom.add(HydroPump);
    movimientosRotom.add(VoltSwitch);
    movimientosRotom.add(WillOWisp);
    movimientosRotom.add(Protect);
    Pokemon RotomWash = new Pokemon("Rotom-Wash",50,tiposRotom,Levitate,157,85,128,125,128,106,movimientosRotom);

    //Garchomp
    ArrayList<Tipo> tiposGarchomp = new ArrayList<>();
    tiposGarchomp.add(Tipo.DRAGÓN);
    tiposGarchomp.add(Tipo.TIERRA); 
    ArrayList<Movimiento> movimientosGarchomp = new ArrayList<>();
    movimientosGarchomp.add(Earthquake);
    movimientosGarchomp.add(DragonClaw);
    movimientosGarchomp.add(RockSlide);
    movimientosGarchomp.add(Protect);
    Pokemon Garchomp = new Pokemon("Garchomp",50,tiposGarchomp,RoughSkin,183,182,115,95,105,154,movimientosGarchomp);

    //Togekiss
    ArrayList<Tipo> tiposTogekiss = new ArrayList<>();
    tiposTogekiss.add(Tipo.HADA);
    tiposTogekiss.add(Tipo.VOLADOR); 
    ArrayList<Movimiento> movimientosTogekiss = new ArrayList<>();
    movimientosTogekiss.add(AirSlash);
    movimientosTogekiss.add(DazzlingGleam);
    movimientosTogekiss.add(CalmMind);
    movimientosTogekiss.add(Protect);
    Pokemon Togekiss = new Pokemon("Togekiss",50,tiposTogekiss,SereneGrace,191,90,115,140,135,113,movimientosTogekiss);

        
    //Metagross
    ArrayList<Tipo> tiposMetagross = new ArrayList<>();
    tiposMetagross.add(Tipo.ACERO);
    tiposMetagross.add(Tipo.PSÍQUICO); 
    ArrayList<Movimiento> movimientosMetagross = new ArrayList<>();
    movimientosMetagross.add(MeteorMash);
    movimientosMetagross.add(ZenHeadbutt);
    movimientosMetagross.add(Earthquake);
    movimientosMetagross.add(Protect);
    Pokemon Metagross = new Pokemon("Metagross",50,tiposMetagross,ClearBody,187,178,150,105,110,110,movimientosMetagross);

    //Milotic
    ArrayList<Tipo> tiposMilotic = new ArrayList<>();
    tiposMilotic.add(Tipo.AGUA); 
    ArrayList<Movimiento> movimientosMilotic = new ArrayList<>();
    movimientosMilotic.add(Scald);
    movimientosMilotic.add(IceBeam);
    movimientosMilotic.add(Recover);
    movimientosMilotic.add(Protect);
    Pokemon Milotic = new Pokemon("Milotic",50,tiposMilotic,Competitive,202,90,125,135,145,101,movimientosMilotic);

    //Arcanine
    ArrayList<Tipo> tiposArcanine = new ArrayList<>();
    tiposArcanine.add(Tipo.FUEGO);
    ArrayList<Movimiento> movimientosArcanine = new ArrayList<>();
    movimientosArcanine.add(FlareBlitz);
    movimientosArcanine.add(ExtremeSpeed);
    movimientosArcanine.add(Snarl);
    movimientosArcanine.add(WillOWisp);
    Pokemon Arcanine = new Pokemon("Arcanine",50,tiposArcanine,Intimidate,187,155,120,115,120,135,movimientosArcanine);
    
        
    //Amoonguss
    ArrayList<Tipo> tiposAmoonguss = new ArrayList<>();
    tiposAmoonguss.add(Tipo.PLANTA);
    tiposAmoonguss.add(Tipo.VENENO); 
    ArrayList<Movimiento> movimientosAmoonguss = new ArrayList<>();
    movimientosAmoonguss.add(Spore);
    movimientosAmoonguss.add(EnergyBall);
    movimientosAmoonguss.add(RagePowder);
    movimientosAmoonguss.add(Protect);
    Pokemon Amoonguss = new Pokemon("Amoonguss",50,tiposAmoonguss,Regenerator,221,95,125,105,130,45,movimientosAmoonguss);


    //Dragapult
    ArrayList<Tipo> tiposDragapult = new ArrayList<>();
    tiposDragapult.add(Tipo.DRAGÓN);
    tiposDragapult.add(Tipo.FANTASMA); 
    ArrayList<Movimiento> movimientosDragapult = new ArrayList<>();
    movimientosDragapult.add(DragonPulse);
    movimientosDragapult.add(ShadowBall);
    movimientosDragapult.add(UTurn);
    movimientosDragapult.add(Protect);
    Pokemon Dragapult = new Pokemon("Dragapult",50,tiposDragapult,ClearBody,185,185,115,85,105,154,movimientosDragapult);

        
    //Excadrill
    ArrayList<Tipo> tiposExcadrill = new ArrayList<>();
    tiposExcadrill.add(Tipo.TIERRA);
    tiposExcadrill.add(Tipo.ACERO); 
    ArrayList<Movimiento> movimientosExcadrill = new ArrayList<>();
    movimientosExcadrill.add(IronHead);
    movimientosExcadrill.add(Earthquake);
    movimientosExcadrill.add(SwordDance);
    movimientosExcadrill.add(Protect);
    Pokemon Excadrill = new Pokemon("Excadrill",50,tiposExcadrill,MoldBreaker,185,185,115,85,105,154,movimientosExcadrill);
       
         
    //Sylveon
    ArrayList<Tipo> tiposSylveon = new ArrayList<>();
    tiposSylveon.add(Tipo.HADA);
    ArrayList<Movimiento> movimientosSylveon = new ArrayList<>();
    movimientosSylveon.add(HyperVoice);
    movimientosSylveon.add(MysticalFire);
    movimientosSylveon.add(CalmMind);
    movimientosSylveon.add(Protect);
    Pokemon Sylveon = new Pokemon("Sylveon",50,tiposSylveon,Pixilate,185,185,115,85,105,154,movimientosSylveon);
       
    ArrayList<Pokemon> Equipo = new ArrayList<>();
    Boolean salir = false;
    int cont = 0;
    do {
            int numero;
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Elegir pokemon\n2. Ver equipo\n3. Salir");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    if (cont>=4){
                        System.out.println("Elige un pokemon: 1.RotomWash\n2.Garchomp\n3.Togekiss\n4.Metagross\n5.Milotic\n6.Arcanine\n7.Amoonguss\n8.Dragapult\n9.Excadrill\n10.Sylveon");
                        numero = scanner.nextInt();
                        scanner.nextLine();
                        switch (numero){
                            case 1:
                                Equipo.add(RotomWash);
                                break;
                            case 2:
                                Equipo.add(RotomWash);
                                break;
                            case 3:
                                Equipo.add(RotomWash);
                                break;
                            case 4:
                                Equipo.add(RotomWash);
                                break;
                            case 5:
                                Equipo.add(RotomWash);
                                break;
                            case 6:
                                Equipo.add(RotomWash);
                                break;
                            case 7:
                                Equipo.add(RotomWash);
                                break;
                            case 8:
                                Equipo.add(RotomWash);
                                break;
                            case 9:
                                Equipo.add(RotomWash);
                                break;
                            case 10:
                                Equipo.add(RotomWash);
                                break;
                            default:
                                System.out.println("No eligistes ningún pokemon");
                                break;
                        cont+=1;
                        break;
                    }}else{
                        System.out.println("Equipo completo");
                    }
                case 2:
                    Equipo.toString();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción non válida");
            }
        } while (!salir);
        }
    }


