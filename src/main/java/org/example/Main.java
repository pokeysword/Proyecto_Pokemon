package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


import org.example.habilidades.*;

import org.example.movimientos.*;


public class Main {
    static void main() {
    //Rotom
    ArrayList<Tipo> tiposRotom = new ArrayList<>();
    tiposRotom.add(Tipo.ELÉCTRICO);
    tiposRotom.add(Tipo.AGUA);

    ArrayList<Movimiento> movimientosRotom = new ArrayList<>();
    movimientosRotom.add(new HydroPump());
    movimientosRotom.add(new VoltSwitch());
    movimientosRotom.add(new WillOWisp());
    movimientosRotom.add(new Protect());
    Pokemon RotomWash = new Pokemon("Rotom-Wash",50,tiposRotom,new Levitate(),157,85,128,125,128,106,movimientosRotom);

    //Garchomp
    ArrayList<Tipo> tiposGarchomp = new ArrayList<>();
    tiposGarchomp.add(Tipo.DRAGÓN);
    tiposGarchomp.add(Tipo.TIERRA); 
    ArrayList<Movimiento> movimientosGarchomp = new ArrayList<>();
    movimientosGarchomp.add(new Earthquake());
    movimientosGarchomp.add(new DragonClaw());
    movimientosGarchomp.add(new RockSlide());
    movimientosGarchomp.add(new Protect());
    Pokemon Garchomp = new Pokemon("Garchomp",50,tiposGarchomp,new RoughSkin(),183,182,115,95,105,154,movimientosGarchomp);

    //Togekiss
    ArrayList<Tipo> tiposTogekiss = new ArrayList<>();
    tiposTogekiss.add(Tipo.HADA);
    tiposTogekiss.add(Tipo.VOLADOR); 
    ArrayList<Movimiento> movimientosTogekiss = new ArrayList<>();
    movimientosTogekiss.add(new AirSlash());
    movimientosTogekiss.add(new DazzlingGleam());
    movimientosTogekiss.add(new CalmMind());
    movimientosTogekiss.add(new Protect());
    Pokemon Togekiss = new Pokemon("Togekiss",50,tiposTogekiss,new SereneGrace(),191,90,115,140,135,113,movimientosTogekiss);

        
    //Metagross
    ArrayList<Tipo> tiposMetagross = new ArrayList<>();
    tiposMetagross.add(Tipo.ACERO);
    tiposMetagross.add(Tipo.PSÍQUICO); 
    ArrayList<Movimiento> movimientosMetagross = new ArrayList<>();
    movimientosMetagross.add(new MeteorMash());
    movimientosMetagross.add(new ZenHeadbutt());
    movimientosMetagross.add(new Earthquake());
    movimientosMetagross.add(new Protect());
    Pokemon Metagross = new Pokemon("Metagross",50,tiposMetagross,new ClearBody(),187,178,150,105,110,110,movimientosMetagross);

    //Milotic
    ArrayList<Tipo> tiposMilotic = new ArrayList<>();
    tiposMilotic.add(Tipo.AGUA); 
    ArrayList<Movimiento> movimientosMilotic = new ArrayList<>();
    movimientosMilotic.add(new Scald());
    movimientosMilotic.add(new IceBeam());
    movimientosMilotic.add(new Recover());
    movimientosMilotic.add(new Protect());
    Pokemon Milotic = new Pokemon("Milotic",50,tiposMilotic,new Competitive(),202,90,125,135,145,101,movimientosMilotic);

    //Arcanine
    ArrayList<Tipo> tiposArcanine = new ArrayList<>();
    tiposArcanine.add(Tipo.FUEGO);
    ArrayList<Movimiento> movimientosArcanine = new ArrayList<>();
    movimientosArcanine.add(new FlareBlitz());
    movimientosArcanine.add(new ExtremeSpeed());
    movimientosArcanine.add(new Snarl());
    movimientosArcanine.add(new WillOWisp());
    Pokemon Arcanine = new Pokemon("Arcanine",50,tiposArcanine, new Intimidacion(),187,155,120,115,120,135,movimientosArcanine);
    
        
    //Amoonguss
    ArrayList<Tipo> tiposAmoonguss = new ArrayList<>();
    tiposAmoonguss.add(Tipo.PLANTA);
    tiposAmoonguss.add(Tipo.VENENO); 
    ArrayList<Movimiento> movimientosAmoonguss = new ArrayList<>();
    movimientosAmoonguss.add(new Spore());
    movimientosAmoonguss.add(new EnergyBall());
    movimientosAmoonguss.add(new RagePowder());
    movimientosAmoonguss.add(new Protect());
    Pokemon Amoonguss = new Pokemon("Amoonguss",50,tiposAmoonguss,new Regenerator(),221,95,125,105,130,45,movimientosAmoonguss);


    //Dragapult
    ArrayList<Tipo> tiposDragapult = new ArrayList<>();
    tiposDragapult.add(Tipo.DRAGÓN);
    tiposDragapult.add(Tipo.FANTASMA); 
    ArrayList<Movimiento> movimientosDragapult = new ArrayList<>();
    movimientosDragapult.add(new DragonPulse());
    movimientosDragapult.add(new ShadowBall());
    movimientosDragapult.add(new UTurn());
    movimientosDragapult.add(new Protect());
    Pokemon Dragapult = new Pokemon("Dragapult",50,tiposDragapult,new ClearBody(),185,185,115,85,105,154,movimientosDragapult);

        
    //Excadrill
    ArrayList<Tipo> tiposExcadrill = new ArrayList<>();
    tiposExcadrill.add(Tipo.TIERRA);
    tiposExcadrill.add(Tipo.ACERO); 
    ArrayList<Movimiento> movimientosExcadrill = new ArrayList<>();
    movimientosExcadrill.add(new IronHead());
    movimientosExcadrill.add(new Earthquake());
    movimientosExcadrill.add(new SwordDance());
    movimientosExcadrill.add(new Protect());
    Pokemon Excadrill = new Pokemon("Excadrill",50,tiposExcadrill,new MoldBreaker(),185,185,115,85,105,154,movimientosExcadrill);
       
         
    //Sylveon
    ArrayList<Tipo> tiposSylveon = new ArrayList<>();
    tiposSylveon.add(Tipo.HADA);
    ArrayList<Movimiento> movimientosSylveon = new ArrayList<>();
    movimientosSylveon.add(new HyperVoice());
    movimientosSylveon.add(new MysticalFire());
    movimientosSylveon.add(new CalmMind());
    movimientosSylveon.add(new Protect());
    Pokemon Sylveon = new Pokemon("Sylveon",50,tiposSylveon,new Pixilate(),185,185,115,85,105,154,movimientosSylveon);
       
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

                    if (cont<4){
                        System.out.println("Elige un pokemon:\n1.RotomWash\n2.Garchomp\n3.Togekiss\n4.Metagross\n5.Milotic\n6.Arcanine\n7.Amoonguss\n8.Dragapult\n9.Excadrill\n10.Sylveon");
                        numero = scanner.nextInt();
                        scanner.nextLine();
                        switch (numero){
                            case 1:
                                Equipo.add(RotomWash);
                                break;
                            case 2:
                                Equipo.add(Garchomp);
                                break;
                            case 3:
                                Equipo.add(Togekiss);
                                break;
                            case 4:
                                Equipo.add(Metagross);
                                break;
                            case 5:
                                Equipo.add(Milotic);
                                break;
                            case 6:
                                Equipo.add(Arcanine);
                                break;
                            case 7:
                                Equipo.add(Amoonguss);
                                break;
                            case 8:
                                Equipo.add(Dragapult);
                                break;
                            case 9:
                                Equipo.add(Excadrill);
                                break;
                            case 10:
                                Equipo.add(Sylveon);
                                break;
                            default:
                                System.out.println("No eligistes ningún pokemon");
                                break;

                    }cont+=1;
                    }else{
                        System.out.println("Equipo completo");
                    }break;
                case 2:
                    for (Pokemon p : Equipo)
                    System.out.println(p.getNombre());
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



