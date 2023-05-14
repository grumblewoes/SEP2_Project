package mediator;

import java.util.ArrayList;

public class MeetingList
{
  private ArrayList<Meeting> list;

  public MeetingList()
  {
    list = new ArrayList<>();
  }

  public void add(Meeting meeting)
  {
    list.add(meeting);
  }

  public Meeting get(int i)
  {
    return list.get(i);
  }

  public int size()
  {
    return list.size();
  }

  @Override public String toString()
  {
    return "MeetingList{" + "list=" + list + '}';
  }

}