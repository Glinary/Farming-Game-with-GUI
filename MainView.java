import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* This class represents the main GUI view of the farming game.
* It includes the a list of frames such as the main frame, player stats' frame, 
* tool frame, and seeds frame. It also contains the farming panel and the option panel.
* It also contains labels that need to be displayed in the main view such as
* the label of the seeds, a day counter, the currency, level, experience, farmer type,
* and additional seed, water, and fertilizer information. Finally, it contains
* buttons that represent the tiles and make the game interactive.
*/
public class MainView {

    private Farm myFarm;
    private JFrame mainFrame;
    private ArrayList<JButton> tileBtnList;
    private JPanel farmLot, optionPanel;
    private JFrame playerStatsFrame, toolFrame, seedsFrame, cropInformationFrame;
    private JTable cropInfoTbl;
    private String columnNames[] = {"Seed Name", "Crop Type", "Harvest Time in Days", "Water Needs", "Fertilizer Needs", "Products Produced", "Seed Cost", "Base Selling Price per Piece", "Experience Yield"};
    private String[][] data = {
        {"Turnip", "Root crop", "2", "1", "0", "1-2", "5", "6", "5"},
        {"Carrot", "Root crop", "3", "1", "0", "1-2", "10", "9", "7.5"},
        {"Potato", "Root crop", "5", "3", "1", "1-10", "20", "3", "12.5"},
        {"Rose", "Flower", "1", "1", "0", "1", "5", "5", "2.5"},
        {"Tulips", "Flower", "2", "2", "0", "1", "10", "9", "5"},
        {"Sunflower", "Flower", "3", "2", "1", "1", "20", "19", "7.5"},
        {"Mango", "Fruit tree", "10", "7", "4", "5-15", "100", "8", "25"},
        {"Apple", "Fruit tree", "10", "7", "5", "10-15", "200", "5", "25"}
    };
    private JButton plowBtn, wateringCanBtn, fertilizerBtn, pickaxeBtn, shovelBtn;
    private JButton turnipBtn, carrotBtn, potatoBtn, roseBtn, tulipsBtn, sunflowerBtn, mangoBtn, appleBtn;
    private JLabel dayCounterLbl, currencyLbl;
    private JButton playerStatsBtn, toolActionBtn, cropInfoActionBtn, seedActionBtn, harvestActionBtn, sleepActionBtn;
    private JLabel levelLbl, experienceLbl, farmerTypeLbl, bonusEarnLbl, seedReducLbl, bonusWaterLbl, bonusFertLbl;


    /**
    * This constructor initializes the value of the farm that will be displayed
    * in the GUI of the main view 
    */
    public MainView(int rockCount) {
        this.myFarm = new Farm(rockCount);
        this.tileBtnList = new ArrayList<JButton>();
        this.levelLbl = new JLabel("     Level: 0");
        this.experienceLbl = new JLabel("     Experience: 0");
        this.farmerTypeLbl = new JLabel("     Farmer Type: Farmer");
        this.bonusEarnLbl = new JLabel("     Bonus Earnings per Produce: +0");
        this.seedReducLbl = new JLabel("     Seed Cost Reduced: +0");
        this.bonusWaterLbl = new JLabel("     Water Bonus Increased: +0");
        this.bonusFertLbl = new JLabel("     Fertilizer Bonus Increased: +0");
        initializeFarm();

    }

    /**
    * This method is responsible for initializing the contents of the farm
    * and displays the farming lot to the user.
    */
    private void initializeFarm() {
        int i = 0;
        this.mainFrame = new JFrame("My Farm");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setBackground(Color.white);
        this.mainFrame.setSize(750, 855);
        

        this.farmLot = new JPanel(new GridLayout(10, 5));
        this.farmLot.setBackground(Color.white);

        for(i = 0; i < 50; i++) {
            JButton tileButton = new JButton();
            if(this.myFarm.getTile(i).getHasRock() == true) {
                tileButton.setBackground(Color.gray);
                tileButton.setText((i + 1) + ": Rock");
            } else if(this.myFarm.getTile(i).getIsPlowed() == false) {
                tileButton.setBackground(Color.orange);
                tileButton.setText((i + 1) + ": Unplowed");
            }
            this.tileBtnList.add(tileButton);
            this.farmLot.add(this.tileBtnList.get(i));
        }

        this.mainFrame.add(this.farmLot, BorderLayout.CENTER);

        this.optionPanel = new JPanel(new GridLayout(8, 1));
        this.dayCounterLbl = new JLabel("     Day: 1");
        this.currencyLbl = new JLabel("     ObjectCoins : 100");

        this.playerStatsBtn = new JButton("View Player Stats");
        this.playerStatsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPlayerStats();
            }
        });

        this.toolActionBtn = new JButton("Use Tool");
        this.toolActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayToolFrame();
            }
        });

        this.cropInfoActionBtn = new JButton("Check Crop Information");
        this.cropInfoActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCropInformationFrame();
            }
        });

        this.seedActionBtn = new JButton("Plant Seed");
        this.seedActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySeedsFrame();
            }
        });

        this.harvestActionBtn = new JButton("Harvest Crop");
        this.harvestActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receivedString;
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        int tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == true) {
                            if(myFarm.getTile(tileIndex).getCrop().getIsHarvestable() == true) {
                                myFarm.getPlayer().harvest(myFarm.getTile(tileIndex));
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Unplowed");
                                tileBtnList.get(tileIndex).setBackground(Color.orange);
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                                setExperience(String.valueOf(myFarm.getPlayer().getExperience()));
                            } else {
                                JOptionPane.showMessageDialog(null, "This crop is not yet harvestable.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "This tile has no crop to harvest.");
                        }
                        
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                

                
            }
        });

        this.sleepActionBtn = new JButton("Sleep");
        this.sleepActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveToNextDay();
            }
        });

        this.optionPanel.add(dayCounterLbl);
        this.optionPanel.add(currencyLbl);
        this.optionPanel.add(playerStatsBtn);
        this.optionPanel.add(toolActionBtn);
        this.optionPanel.add(cropInfoActionBtn);
        this.optionPanel.add(seedActionBtn);
        this.optionPanel.add(harvestActionBtn);
        this.optionPanel.add(sleepActionBtn);

        this.mainFrame.add(this.optionPanel, BorderLayout.EAST);
        

        this.mainFrame.setVisible(true);

    }

    /**
    * This method displays the current stats of the player
    */
    private void displayPlayerStats() {
        this.playerStatsFrame = new JFrame("Current Player Stats");
        this.playerStatsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.playerStatsFrame.setLocationRelativeTo(null);
        this.playerStatsFrame.setLayout(new GridLayout(7, 1));
        this.playerStatsFrame.setSize(300, 500);

        this.playerStatsFrame.add(this.levelLbl);
        this.playerStatsFrame.add(this.experienceLbl);
        this.playerStatsFrame.add(this.farmerTypeLbl);
        this.playerStatsFrame.add(this.bonusEarnLbl);
        this.playerStatsFrame.add(this.seedReducLbl);
        this.playerStatsFrame.add(this.bonusWaterLbl);
        this.playerStatsFrame.add(this.bonusFertLbl);

        this.playerStatsFrame.setVisible(true);
    }

    /**
    * This method displays the frame of the tools that can be used for the game
    */
    private void displayToolFrame() {
        this.toolFrame = new JFrame("Choose a Tool");
        this.toolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.toolFrame.setLocationRelativeTo(null);
        this.toolFrame.setLayout(new GridLayout(5, 1));
        this.toolFrame.setSize(200, 500);

        this.plowBtn = new JButton();
        this.plowBtn.setText("Plow");

        this.plowBtn.addActionListener(new ActionListener() {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {

                receivedString = JOptionPane.showInputDialog("Select a tile: ");

                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getIsPlowed() == false && myFarm.getTile(tileIndex).getHasRock() == false) {
                            tileBtnList.get(tileIndex).setBackground(Color.green);
                            tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Plowed");
                            myFarm.getPlayer().plow(myFarm.getTile(tileIndex));
                            setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            setExperience(String.valueOf(myFarm.getPlayer().getExperience()));
                            setLevel(String.valueOf(myFarm.getPlayer().getLevel()));  
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plow this tile.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "This tile does not exist.");
                    }    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                toolFrame.dispose();
            
            }
        });

        
        this.wateringCanBtn = new JButton();
        this.wateringCanBtn.setText("Watering Can");
        this.wateringCanBtn.addActionListener(new ActionListener() {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {

                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            if(myFarm.getTile(tileIndex).getHasCrop() == true) {
                                myFarm.getPlayer().water(myFarm.getTile(tileIndex));
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                                setExperience(String.valueOf(myFarm.getPlayer().getExperience()));
                                setLevel(String.valueOf(myFarm.getPlayer().getLevel()));
                            } else {
                                JOptionPane.showMessageDialog(null, "You cannot water an unplanted tile.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot water this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                toolFrame.dispose();
            }
        });

        this.fertilizerBtn = new JButton();
        this.fertilizerBtn.setText("Fertilizer");
        this.fertilizerBtn.addActionListener(new ActionListener() {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {

                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            if(myFarm.getTile(tileIndex).getHasCrop() == true) {
                                myFarm.getPlayer().fertilize(myFarm.getTile(tileIndex));
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                                setExperience(String.valueOf(myFarm.getPlayer().getExperience()));
                                setLevel(String.valueOf(myFarm.getPlayer().getLevel()));
                                checkIfGameOver();
                            } else {
                                JOptionPane.showMessageDialog(null, "You cannot add fertilize to on unplanted tile.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot add fertilize in this tile.");
                        }
                        
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                toolFrame.dispose();
            }
        });

        this.pickaxeBtn = new JButton();
        this.pickaxeBtn.setText("Pickaxe");
        this.pickaxeBtn.addActionListener(new ActionListener() {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {

                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasRock() == true) {
                            tileBtnList.get(tileIndex).setBackground(Color.orange);
                            tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Unplowed");
                            myFarm.getPlayer().pickaxe(myFarm.getTile(tileIndex));
                            setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            setExperience(String.valueOf(myFarm.getPlayer().getExperience()));
                            setLevel(String.valueOf(myFarm.getPlayer().getLevel()));
                            checkIfGameOver();
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot use the pickaxe in this tile because there is no rock.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                toolFrame.dispose();
            }
        });

        this.shovelBtn = new JButton();
        this.shovelBtn.setText("Shovel");
        this.shovelBtn.addActionListener(new ActionListener() {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {

                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == true) {
                            tileBtnList.get(tileIndex).setBackground(Color.orange);
                            tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Unplowed");
                        } else {
                            JOptionPane.showMessageDialog(null, "Nothing happened in this tile.");
                        }
                        myFarm.getPlayer().shovel(myFarm.getTile(tileIndex));
                        setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                        setExperience(String.valueOf(myFarm.getPlayer().getExperience()));
                        setLevel(String.valueOf(myFarm.getPlayer().getLevel()));
                        checkIfGameOver();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                toolFrame.dispose();
            }
        });
        
        this.toolFrame.add(this.plowBtn);
        this.toolFrame.add(this.wateringCanBtn);
        this.toolFrame.add(this.fertilizerBtn);
        this.toolFrame.add(this.pickaxeBtn);
        this.toolFrame.add(this.shovelBtn);
        this.toolFrame.setVisible(true);
    }

    /**
     * This method displays the frame of crop information
     */
    private void displayCropInformationFrame() {
        this.cropInformationFrame = new JFrame("General Crop Information");
        this.cropInformationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.cropInformationFrame.setLocationRelativeTo(null);
        this.cropInformationFrame.setLayout(new BorderLayout());
        this.cropInformationFrame.setSize(1000, 200);

        this.cropInfoTbl = new JTable(this.data, this.columnNames);
        this.cropInformationFrame.add(this.cropInfoTbl.getTableHeader(), BorderLayout.NORTH);
        this.cropInformationFrame.add(this.cropInfoTbl, BorderLayout.CENTER);

        this.cropInformationFrame.setVisible(true);
    }

    /**
    * This method displays the frame of the seeds that can be planted in the game
    */
    private void displaySeedsFrame() {
        this.seedsFrame = new JFrame("Select a seed");
        this.seedsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.seedsFrame.setLocationRelativeTo(null);
        this.seedsFrame.setLayout(new GridLayout(8, 2));
        this.seedsFrame.setSize(200, 500);

        this.turnipBtn = new JButton();
        this.turnipBtn.setText("Turnip");
        this.turnipBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 1);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Turnip == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Turnip");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    } 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });

        this.carrotBtn = new JButton();
        this.carrotBtn.setText("Carrot");
        this.carrotBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 2);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Carrot == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Carrot");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });

        this.potatoBtn = new JButton();
        this.potatoBtn.setText("Potato");
        this.potatoBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 3);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Potato == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Potato");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });

        this.roseBtn = new JButton();
        this.roseBtn.setText("Rose");
        this.roseBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 4);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Rose == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Rose");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });
        
        this.tulipsBtn = new JButton();
        this.tulipsBtn.setText("Tulips");
        this.tulipsBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 5);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Tulips == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Tulips");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });
        
        this.sunflowerBtn = new JButton();
        this.sunflowerBtn.setText("Sunflower");
        this.sunflowerBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 6);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Sunflower == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Sunflower");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } 
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });

        this.mangoBtn = new JButton();
        this.mangoBtn.setText("Mango");
        this.mangoBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 7);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Mango == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Mango");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } else {
                                JOptionPane.showMessageDialog(null, "You need more space to plant on this tile.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });

        this.appleBtn = new JButton();
        this.appleBtn.setText("Apple");
        this.appleBtn.addActionListener(new ActionListener () {
            String receivedString;
            int tileIndex;
            @Override
            public void actionPerformed(ActionEvent e) {
                receivedString = JOptionPane.showInputDialog("Select a tile: ");
                try {
                    if(receivedString.length() > 0) {
                        tileIndex = Integer.parseInt(receivedString) - 1;
                        if(myFarm.getTile(tileIndex).getHasCrop() == false && myFarm.getTile(tileIndex).getIsPlowed() == true) {
                            myFarm.getPlayer().plantSeed(myFarm.getTile(tileIndex), myFarm, 8);
                            if(myFarm.getTile(tileIndex).getCrop() instanceof Apple == true) {
                                tileBtnList.get(tileIndex).setText((tileIndex + 1) + ": Apple");
                                setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                            } else {
                                JOptionPane.showMessageDialog(null, "You need more space to plant on this tile.");
                            }
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "You cannot plant in this tile.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "This input is invalid.");
                }
                
                seedsFrame.setVisible(false);
            }

        });
        
        this.seedsFrame.add(this.turnipBtn);
        this.seedsFrame.add(this.carrotBtn);
        this.seedsFrame.add(this.potatoBtn);
        this.seedsFrame.add(this.roseBtn);
        this.seedsFrame.add(this.tulipsBtn);
        this.seedsFrame.add(this.sunflowerBtn);
        this.seedsFrame.add(this.mangoBtn);
        this.seedsFrame.add(this.appleBtn);
        
        this.seedsFrame.setVisible(true);
    }

    /**
    * This method updates the game to move forward to the next day
    */
    private void moveToNextDay() {
        int i;
        if(myFarm.getPlayer().getLevel() >= 5 && myFarm.getPlayer().getFarmerType().equals("Farmer")) {
            showRegistrationOption();
        } else if(myFarm.getPlayer().getLevel() >= 10 && myFarm.getPlayer().getFarmerType().equals("Registered Farmer")) {
            showRegistrationOption();
        } else if(myFarm.getPlayer().getLevel() >= 15 && myFarm.getPlayer().getFarmerType().equals("Distinguished Farmer")) {
            showRegistrationOption();
        }
        myFarm.goToNextDay();
        setDayCounter(String.valueOf(myFarm.getDayCount()));
        for(i = 0; i < 50; i++) {
            if(myFarm.getTile(i).getHasCrop() == true) {
                if(myFarm.getTile(i).getCrop().getIsHarvestable() == true)
                    tileBtnList.get(i).setText((i+ 1) + ": Harvestable");
                else if(myFarm.getTile(i).getCrop().getIsWithered() == true) {
                    tileBtnList.get(i).setText((i+ 1) + ": Withered");
                    tileBtnList.get(i).setBackground(Color.red);
                }
            }  
        }
        checkIfGameOver();
    }

    /**
    * This method shows the frame for the player to update their class/farmer type
    */
    private void showRegistrationOption() {
        int reply;
        if(myFarm.getPlayer().getLevel() >= 5 && myFarm.getPlayer().getFarmerType().equals("Farmer")) {
            reply = JOptionPane.showConfirmDialog(null, "Would you like to register as a Registered Farmer for 200 ObjectCoins?", "Congratulations! You have reached the stats needed to become a Registered Farmer!", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION) {
                if(myFarm.getPlayer().getObjectCoins() >= 200) {
                    myFarm.getPlayer().register(1);
                    setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                    setFarmerType("Registered Farmer");
                    setBonusEarn("+1");
                    setSeedReduc("-1");
                }
            }
        }
        else if(myFarm.getPlayer().getLevel() >= 10 && myFarm.getPlayer().getFarmerType().equals("Registered Farmer")){
            reply = JOptionPane.showConfirmDialog(null, "Would you like to register as a Distinguished Farmer for 300 ObjectCoins?", "Congratulations! You have reached the stats needed to become a Distinguised Farmer!", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION) {
                if(myFarm.getPlayer().getObjectCoins() >= 300) {
                    myFarm.getPlayer().register(1);
                    setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                    setFarmerType("Distinguised Farmer");
                    setBonusEarn("+2");
                    setSeedReduc("-2");
                    setBonusWater("+1");
                }
            }
        }
        else if(myFarm.getPlayer().getLevel() >= 15 && myFarm.getPlayer().getFarmerType().equals("Distinguished Farmer")){
            reply = JOptionPane.showConfirmDialog(null, "Would you like to register as a Legendary Farmer for 400 ObjectCoins?", "Congratulations! You have reached the stats needed to become a Legendary Farmer!", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.YES_OPTION) {
                if(myFarm.getPlayer().getObjectCoins() >= 300) {
                    myFarm.getPlayer().register(1);
                    setCurrency(String.valueOf(myFarm.getPlayer().getObjectCoins()));
                    setFarmerType("Legendary Farmer");
                    setBonusEarn("+4");
                    setSeedReduc("-3");
                    setBonusWater("+2");
                    setBonusFert("+1");
                }
            }
        }

    }
    
    /**
    * This checks if the game should end
    */
    private void checkIfGameOver() {
        boolean endGame = myFarm.checkIfGameShouldEnd();
        if(endGame == true) {
            boolean exitGame = showEndGameDialog();
            if(exitGame == false) {
                if(this.playerStatsFrame != null)
                    this.playerStatsFrame.dispose();
                if(this.toolFrame != null)
                    this.toolFrame.dispose();
                if(this.cropInformationFrame != null)
                    this.cropInformationFrame.dispose();
                if(this.seedsFrame != null)
                    this.seedsFrame.dispose();
                this.mainFrame.dispose();
            } else {
                this.mainFrame.dispose();
                WelcomeGUI welcomeGUI = new WelcomeGUI();
                
/* 

                this.myFarm = new Farm(15);
                this.tileBtnList = new ArrayList<JButton>();
                this.levelLbl = new JLabel("     Level: 0");
                this.experienceLbl = new JLabel("     Experience: 0");
                this.farmerTypeLbl = new JLabel("     Farmer Type: Farmer");
                this.bonusEarnLbl = new JLabel("     Bonus Earnings per Produce: +0");
                this.seedReducLbl = new JLabel("     Seed Cost Reduced: +0");
                this.bonusWaterLbl = new JLabel("     Water Bonus Increased: +0");
                this.bonusFertLbl = new JLabel("     Fertilizer Bonus Increased: +0");
                initializeFarm();
*/
            }
        }
    }

    /**
    * This activates when the game ends, and asks the user if they want to quit or play again.
    * @return - returns true if player wants to play again, and returns false to quit the game
    */
    private boolean showEndGameDialog() {
        int reply = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * This method sets the current count of the day that is displayed in the game
    * @param changedDay - the day count that is visually presented to the screen
    */
    private void setDayCounter(String changedDay) {
        this.dayCounterLbl.setText("     Day: " + changedDay);
    }

    /**
    * This method sets the currency of the player that is displayed in the game
    * @param changedCurrency - the amount of currency that is visually presented in the game
    */
    private void setCurrency(String changedCurrency) {
        this.currencyLbl.setText("     ObjectCoins: " + changedCurrency);
    }

    /**
    * This method updates the current level of the player
    * @param changedLevel - the current level that is displayed to the screen 
    */
    private void setLevel(String changedLevel) {
        this.levelLbl.setText("     Level: " + changedLevel);
    }

    /**
    * This method updates the current experience of the player
    * @param changedExperience - the current experience that is displayed to the screen
    */
    private void setExperience(String changedExperience) {
        this.experienceLbl.setText("     Experience: " + changedExperience);
    }

    /**
    * This method updates the current farmer type of the player
    * @param changedType - the current farmer type of the player that is displayed to the screen
    */
    private void setFarmerType(String changedType) {
        this.farmerTypeLbl.setText("     Farmer Type: " + changedType);
    }

    private void setBonusEarn(String changedIncrease) {
        this.bonusEarnLbl.setText("     Bonus Earnings per Produce: " + changedIncrease);
    }

    /**
    * This method updates the current amount of cost that is reduced from buying a seed
    * @param changedReduc - the discount applied that is visually presented to the screen
    */
    private void setSeedReduc(String changedReduc) {
        this.seedReducLbl.setText("     Seed Cost Reduced: " + changedReduc);
    }

    /**
    * This method updates the bonus water amount 
    *@param changedIncrease - the bonus water amount visually presented to the screen
    */ 
    private void setBonusWater(String changedIncrease) {
        this.bonusWaterLbl.setText("     Water Bonus Increased: " + changedIncrease);
    }

    /**
    * This method updates the bonu fertilizer amount
    *@param changedIncrease - the bonus fertilizer amount visually presented to the screen
    */
    private void setBonusFert(String changedIncrease) {
        this.bonusFertLbl.setText("     Fertilizer Bonus Increased: " + changedIncrease);
    }

}
