package Animation;

public class animationKeyHolder {
    String key; int idx;
    public animationKeyHolder(String key,int idx){
        this.key = key;
        this.idx = idx;
    }

    public int getIdx(){
        return idx;
    }
    public String getKey() {
        return key;
    }
}
