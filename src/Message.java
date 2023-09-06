import java.util.Map;

public class Message extends EncodingUtils{

    String plainTextMessage;
    String encodedMessage;
    String decodedMessage;

    public String getPlainTextMessage() {
        return plainTextMessage;
    }

    public void setPlainTextMessage(String plainTextMessage) {
        this.plainTextMessage = plainTextMessage;
    }

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public void setEncodedMessage(String encodedMessage) {
        this.encodedMessage = encodedMessage;
    }

    public String getDecodedMessage() {
        return decode(this.plainTextMessage);
    }

    public void setDecodedMessage(String decodedMessage) {
        this.decodedMessage = decodedMessage;
    }

    public String encode(String plainText){
        this.encodedMessage = String.valueOf(EncoderApp.offsetCharacter);
        int indexOffset;
        int referenceTableSize = EncoderApp.referenceTable.size();
        int index = 0;
        char[] characters = plainText.toCharArray();
        for(Integer key: EncoderApp.referenceTable.keySet()){
            if(EncoderApp.referenceTable.get(key).equals(EncoderApp.offsetCharacter)){
                index = key;
                break;
            }
        }

        //encoding
        for (char ch: characters) {
            if(EncoderApp.referenceTable.containsValue(ch)){
                for(Map.Entry<Integer,Character> mapping: EncoderApp.referenceTable.entrySet()){
                    if(mapping.getValue().equals(ch)){
                        indexOffset = (mapping.getKey()-index<0) ? (referenceTableSize+mapping.getKey()-index) : (mapping.getKey()-index);
                        this.encodedMessage+=EncoderApp.referenceTable.get(indexOffset);
                        break;
                    }
                }
            }else {
                this.encodedMessage+=ch;
            }

        }
        return this.encodedMessage;
    }
    public String decode(String encodedText){
        char character = encodedText.toUpperCase().charAt(0);
        StringBuilder str = new StringBuilder();
        int indexOffset;
        int index = 0;
        int referenceTableSize = EncoderApp.referenceTable.size();
        if(EncoderApp.referenceTable.containsValue(character)){
            char[] characters = encodedText.substring(1).toCharArray();
            for(Integer key: EncoderApp.referenceTable.keySet()){
                if(EncoderApp.referenceTable.get(key).equals(character)){
                    index = key;
                    break;
                }
            }
            for (char ch: characters) {
                if(EncoderApp.referenceTable.containsValue(ch)){
                    for(Map.Entry<Integer,Character> mapping: EncoderApp.referenceTable.entrySet()){
                        if(mapping.getValue().equals(ch)){
                            indexOffset = (mapping.getKey()+index>=referenceTableSize) ? (mapping.getKey()+index-referenceTableSize) : (mapping.getKey()+index);
                            str.append(EncoderApp.referenceTable.get(indexOffset));
                            break;
                        }
                    }
                }else {
                    str.append(ch);
                }

            }
        }
        return str.toString();
    }
}
