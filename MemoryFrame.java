public class MemoryFrame
{
    private String id;
    private boolean star;

    public MemoryFrame(String id)
    {
        this.id = id;
        this.star = true;
    }

    public String getMFId()
    {
        return id;
    }
    public Boolean getStar()
    {
        return star;
    }
    public void starTrue()
    {
        star = true;
    }
    public void starFalse()
    {
        star = false;
    }
}