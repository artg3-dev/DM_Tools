/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmtools.filehandling;

import dmtools.game.entities.DNDEntity;
import dmtools.game.entities.PC;
import dmtools.playermgmt.Party;
import dmtools.playermgmt.PlayerParty;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.imageio.IIOException;

/**
 *
 * @author A3
 */
public class FileHandler {

    public static final int PC_FILE = 9;
    public static final int PLAYER_PARTY_FILE = 13;
    public static final int MONSTER_FILE = 17;
    public static final int NPC_FILE = 19;

    public FileHandler() {
    }

    public static ReadWritable loadFromName(String name, int fileType)
            throws IOException {
        InputStream iStream;
        Properties properties = new Properties();

        //creates a file of the given type with the given name
        File file = null;
        switch (fileType) {
            case PC_FILE:
                file = new File(PC.getFilePath(name));
                break;
                
            case PLAYER_PARTY_FILE:
                file = new File(PlayerParty.getFilePath(name));
                break;
        }
        
        //This is if an unsupported filetype is given
        if (file == null) {
            throw new FileNotFoundException("File type not supported");
        }
        
        //if the file exists, this is where it is read from and returned
        if (file.isFile()) {
            iStream = new FileInputStream(file);
            properties.load(iStream);
            iStream.close();
            switch (fileType) {
                case PC_FILE:
                    return PCFileHandler.read(properties);
                case PLAYER_PARTY_FILE:
                    return PartyFileHandler.read(properties, 
                            PartyFileHandler.PLAYER_PARTY);
            }
        } else {
            throw new FileNotFoundException("No such file found.");
        }
        return null;
    }

    public static void saveFromInstance(ReadWritable target, int fileType)
            throws IOException {
        String fileName = target.getFilePath();
        File file = new File(fileName);
        if (!file.isFile()) {//if the file doesn't exists
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException(
                        "Something went wrong creating a new file");
            }
        }

        //once file is found/created, the information is written to the file
        try {
            switch (fileType) {
                case PC_FILE:
                    PCFileHandler.write((PC) target, file);
                    break;
                case PLAYER_PARTY_FILE:
                    PartyFileHandler.write((Party)target, 
                            PartyFileHandler.PLAYER_PARTY, file);
                    Party p = (PlayerParty) target;
                    for (DNDEntity i : p.getMembers()) {
                        PC pc = (PC)i;
                        PCFileHandler.write(pc, new File(pc.getFilePath()));
                    }
                    break;
            }

        } catch (IOException e) {
            throw new IOException("Something went wrong writing to the file at "
                    + file.getPath());
        }
    }

}
