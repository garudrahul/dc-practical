import java.util.*;

public class GFS_Simulation {

    // Chunk Server class
    static class ChunkServer {
        String name;
        HashMap<String, String> storage = new HashMap<>();

        ChunkServer(String name) {
            this.name = name;
        }

        void storeChunk(String chunkId, String data) {
            storage.put(chunkId, data);
        }

        String readChunk(String chunkId) {
            return storage.getOrDefault(chunkId, "Not Found");
        }
    }

    // Master class
    static class Master {
        HashMap<String, List<String>> fileTable = new HashMap<>();

        void createFile(String fileName, List<String> chunks) {
            fileTable.put(fileName, chunks);
        }

        List<String> getChunks(String fileName) {
            return fileTable.getOrDefault(fileName, new ArrayList<>());
        }
    }

    public static void main(String[] args) {

        Master master = new Master();

        ChunkServer server1 = new ChunkServer("Server1");
        ChunkServer server2 = new ChunkServer("Server2");

        // Store chunks
        server1.storeChunk("chunk1", "Hello ");
        server2.storeChunk("chunk2", "World!");

        // Create file mapping
        master.createFile("file1", Arrays.asList("chunk1", "chunk2"));

        // Read file
        List<String> chunks = master.getChunks("file1");

        String result = "";

        for (String chunk : chunks) {
            if (chunk.equals("chunk1")) {
                result += server1.readChunk(chunk);
            } else {
                result += server2.readChunk(chunk);
            }
        }

        System.out.println("File Content: " + result);
    }
}
