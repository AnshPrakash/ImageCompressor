import java.util.*;
import java.io.*;
public class LinkedListImage implements CompressedImageInterface {
  MyArrayList arrayOfLinkedList;
  int[] Dimensions=new int[2];
  int[] noOfBlackPixel;
	public LinkedListImage(String filename)
	{
		//you need to implement this
        try{
            FileReader fr=new FileReader(filename);
		 	      BufferedReader br=new BufferedReader(fr);
		 	      String s;
		 	      String[] k;
		 	      s=br.readLine();
            k=s.split(" ");
            int[][] myint=new int[Integer.parseInt(k[0])][Integer.parseInt(k[1])];
            String[][] g=new String[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
            String[] tempst;
            int l=0;
            Dimensions[0]=Integer.parseInt(k[0]);
            Dimensions[1]=Integer.parseInt(k[1]);
		 	while((s=br.readLine())!=null)
		 	{
               tempst=s.split(" ");
               for (int j=0;j<tempst.length;j++)
               	   g[l][j]=tempst[j];
               l++;
		 	}
		 	for (int i=0;i<g.length;i++)
		 		for(int j=0;j<g[0].length;j++)
		 		{ 
                  myint[i][j]=Integer.parseInt(g[i][j]);
		 		}
            toCompressed(myint);
		    
          }
          catch (FileNotFoundException e)
          {
          	System.out.println(e);
          }  
          catch(IOException e)
          {
          	System.out.println(e);
          }
          catch(Exception e)
          {
          	System.out.println(e);
          }
        

		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

    public LinkedListImage(boolean[][] grid, int width, int height)
    {
		//you need to implement this
		 Dimensions= new int[2];
		 Dimensions[0]=height;
		 Dimensions[1]=width;
        int[][] myint=new int[height][width];
        for(int i=0;i<height;i++)
        	for (int j=0;j<width;j++)
        	{ 
        		myint[i][j]=grid[i][j]? 1:0;

        	}
         toCompressed(myint);
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    public boolean getPixelValue(int x, int y) throws PixelOutOfBoundException
    {
		//you need to implement this
    if(x>=Dimensions[0] || y>=Dimensions[1]||x<0 ||y<0)
    {
      throw new PixelOutOfBoundException("PixelOutOfBoundException");
    }
		Node k;
		boolean interval=false;
		k=arrayOfLinkedList.array[x].head;
		while(k!=null)
		{ 
			if( y < k.element && k.knowWhat==0)
			    {   //System.out.print("0");
			   	 	return true;	
	            }
			else if(y>=k.element && k.knowWhat==0 )
		    	{
		    		 interval=true;
                        
    			}
    		else if(y<=k.element && k.knowWhat==1 )
    		    {
                    if(interval==true)
                    	{ //System.out.print("1");
                    	  return false;
                    	}
                    else
                    	interval=false;
    		    }	
            k=k.next;
				
	    }
		
		return true;
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }
    //////////
    //////Special Constructor for deep copying
    public LinkedListImage(CompressedImageInterface img)
    { 
         Node just;
        String[] s=img.toStringCompressed().split(",");
        String[] s1;
        String[] d=s[0].split(" ");
        Dimensions[0]=Integer.parseInt(d[0]);
        Dimensions[1]=Integer.parseInt(d[1]); 
        noOfBlackPixel=new int[Dimensions[0]];
        int temp=0;      
        arrayOfLinkedList=new MyArrayList(Dimensions[0]);
        for(int i=0;i<Dimensions[0];i++)
        { 
          s1=s[i+1].split(" ");
          //System.out.println(s1[0]);
          for(int j=0;j<s1.length;j++)
          { //System.out.println(s1[j]);
            //s1[j]=s1[j].strip();
            if(!s1[j].equals("-1") && !s1[j].equals("") )
            { //because of empty element shift
              if(j%2!=0)
              {
                  just=new Node(Integer.parseInt(s1[j]),0);
                  arrayOfLinkedList.array[i].add(just);
                  temp=just.element;

              }
              else
              { 
                  just=new Node(Integer.parseInt(s1[j]),1);
                  arrayOfLinkedList.array[i].add(just);
                  noOfBlackPixel[i]=just.element-temp+1;
              } 
            }
            else if(!s1[j].equals(""))
            {
                just=new Node(-1,-1);
                arrayOfLinkedList.array[i].add(just);
            }
            
          }
         
        }



    }
    public void setPixelValue(int x, int y, boolean val)throws PixelOutOfBoundException
    {
		    //you need to implement this
        if(x>=Dimensions[0] || y>=Dimensions[1]||x<0 ||y<0)
        {
          throw new PixelOutOfBoundException("PixelOutOfBoundException");
        }
        //String s="hello";
        Node temp=arrayOfLinkedList.array[x].head;
        Node l=null;
        Node k;
        k=temp.next;
        if(k!=null)
         l=k.next;

        Node value,value1;
        if(temp.element==-1 && val==false)
        { 
          temp.element=y;
          temp.knowWhat=0;
          value=new Node(y,1);
          arrayOfLinkedList.array[x].add(value);
          value=new Node(-1,-1);
          arrayOfLinkedList.array[x].add(value);
          noOfBlackPixel[x]+=1;
          return;
          
        } 
        if(k!=null)
        {  //System.out.println("temp.element "+temp.element+" y" + y +" x "+x);
          while(temp.element!=-1)
         { //if(x!=1 && y!=6 ) 
           //System.out.println("False "+"temp.element: "+temp.element +" k.element: "+k.element+" l.element: "+l.element+" x: " +x+" y: " +y +s);  
          if(val==false )
           {
            if(y<l.element)
            {
              if(y>k.element)
              {
                if(y==k.element+1)
                { 
                  if(y==l.element-1)
                  {
                    temp.next=l.next;
                    noOfBlackPixel[x]+=1;
                    //temp.next=k;
                    //l=k.next;

                    return;
                  } 
                  else
                  { k.element=y;
                    //value =new Node(y,1);
                    //temp.next=value;
                    //value.next=l;
                    noOfBlackPixel[x]+=1;
                    return;
                  }

                }
                else if(y==l.element-1)
                {
                    l.element=y;
                    noOfBlackPixel[x]+=1;
                    return;
                }
                else
                {  
                  value=new Node(y,0);
                  value1=new Node(y,1);
                  value1.next=l;
                  value.next=value1;
                  k.next=value;
                  noOfBlackPixel[x]+=1;
                  return;
                }

              }
            }
            else if(l.element==-1)
            { 
              if(k.element<y)
              {
                if(k.element+1==y)
                {
                  k.element=y;
                  noOfBlackPixel[x]+=1;
                  return;
                }
                else
                {
                  value=new Node(y,0);
                  value1=new Node(y,1);
                  value.next=value1;
                  value1.next=l;
                  k.next=value;
                  noOfBlackPixel[x]+=1;
                  return;
                }
              }
            }
            if(y>=temp.element && y<=k.element)
                   {//System.out.println("I am already zero");
                     return;
                   }
            if(y<temp.element)
              {
                if(y+1== temp.element)
                {
                  temp.element=y;
                  noOfBlackPixel[x]+=1;
                  return;
                }
                else
                {
                  
                  //System.out.println("I m here");
                  value=new Node(y,0);
                  value1=new Node(y,1);
                  value.next=value1;
                  value1.next=temp;
                  arrayOfLinkedList.array[x].head=value;
                  noOfBlackPixel[x]+=1;
                  return;
                }
              } 
            if(y>=l.element)
            {//System.out.println("I m traversing");
             //System.out.println("FalseSpecial "+"temp.element: "+temp.element +" k.element: "+k.element+" l.element: "+l.element+" x: " +x+" y: " +y +s);
             //s="yo";
             temp=l;
             k=temp.next;
             if(k!=null)
              l=k.next;
             }
           } 
           else if(val==true)
           {
             if(temp.element<=y && y<=k.element)
             {
               if(y==temp.element)
                {
                  temp.element=y+1;
                  noOfBlackPixel[x]-=1;
                  return;
                } 
               else if(y==k.element)
                 {
                   k.element=k.element-1;
                   noOfBlackPixel[x]-=1;
                   return;
                 }
               else
               {
                 value=new Node(y-1,1);
                 value1=new Node(y+1,0);
                 value1.next=k;
                 value.next=value1;
                 temp.next=value;
                 noOfBlackPixel[x]-=1;
                 return;
               }
             }
             else
             {
               temp=k.next;
               k=temp.next;
               if(temp.element==-1)
                return;
               
             }
           }
           

          }
        }
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    public int[] numberOfBlackPixels()
    {
		//you need to implement this
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		return noOfBlackPixel;
    }
    
    public void invert()
    {
		 //you need to implement this

      
      Node value;
      MyArrayList newArrayofLinkedList=new MyArrayList(arrayOfLinkedList.array.length);
      Node k,l;
      for(int i=0;i<arrayOfLinkedList.array.length;i++)
      {  k=arrayOfLinkedList.array[i].head;
         l=k.next;
         if(0<k.element)
          {
            value=new Node(0,0);
            newArrayofLinkedList.array[i].add(value);
            value=new Node(k.element-1,1);
            newArrayofLinkedList.array[i].add(value);
            
          }
         if(k.knowWhat==-1)
         {
            value=new Node(0,0);
            newArrayofLinkedList.array[i].add(value);
            value=new Node(Dimensions[1]-1,1);
            newArrayofLinkedList.array[i].add(value);
            
         }
        while(k.knowWhat!=-1)
        { 
          k=l.next;
          if(k.element!=-1)
          {
            value= new Node(l.element+1,0);
            newArrayofLinkedList.array[i].add(value);
            value=new Node(k.element-1,1);
            newArrayofLinkedList.array[i].add(value);
            l=k.next;
          }
          else if(l.element<Dimensions[1]-1)
          {
            value= new Node(l.element+1,0);
            newArrayofLinkedList.array[i].add(value);
            value= new Node(Dimensions[1]-1,1);
            newArrayofLinkedList.array[i].add(value);

          }

        }
        value =new Node(-1,-1);
        newArrayofLinkedList.array[i].add(value);
        noOfBlackPixel[i]=Dimensions[1]-noOfBlackPixel[i]; 
      }
    arrayOfLinkedList=newArrayofLinkedList;
		 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }
    
    public void performAnd(CompressedImageInterface img) throws BoundsMismatchException
    {
		  //you need to implement this
      this.invert();
      CompressedImageInterface im=new LinkedListImage(img);
      im.invert();
      
      try
        {
           this.performOr(im);     
        }
        catch (BoundsMismatchException e)
        {
            throw new BoundsMismatchException("Images of different size");
        }
      this.invert();
		  
      //throw new java.lang.UnsupportedOperationException("Not implemented yet.");

    }
    
    public void performOr(CompressedImageInterface img)  throws BoundsMismatchException
    {
		 //you need to implement this
        Node value1,value2;
        Node k1,k2,l1,l2;
        MyArrayList OrArray= new MyArrayList(this.arrayOfLinkedList.array.length);
        ///////////////////
        Node just;
        String[] s=img.toStringCompressed().split(",");
        String[] s1;
        String[] d=s[0].split(" ");
        int[] u={-1,-1};
        for(int i=0;i<d.length;i++)
        {
            u[i]=Integer.parseInt(d[i]);
        }
        if(Dimensions[0]!=u[0] || Dimensions[1]!=u[1])
        {
          throw new BoundsMismatchException("Images Of Different Size: Invalid_Operttion");
        }
        MyArrayList Imgarray=new MyArrayList(this.arrayOfLinkedList.array.length);
        for(int i=0;i<this.arrayOfLinkedList.array.length;i++)
        { 
          s1=s[i+1].split(" ");
          //System.out.println(s1[0]);
          for(int j=0;j<s1.length;j++)
          { //System.out.println(s1[j]);
            //s1[j]=s1[j].strip();
            if(!s1[j].equals("-1") && !s1[j].equals("") )
            { //because of empty element shift
              if(j%2!=0)
              {
                  just=new Node(Integer.parseInt(s1[j]),0);
                  Imgarray.array[i].add(just);
              }
              else
              { 
                  just=new Node(Integer.parseInt(s1[j]),1);
                  Imgarray.array[i].add(just);
              } 
            }
            else if(!s1[j].equals(""))
            {
                just=new Node(-1,-1);
                Imgarray.array[i].add(just);
            }
            
          } 
         

        } 
        /*for(int i=0;i<Imgarray.array.length;i++)
        { Node b;
          b=Imgarray.array[i].head;
          while(b.element!=-1)
          {
            System.out.print(b.element+" ");
            b=b.next;
          }
          System.out.println(b.element);
        }*/
      /////////////////////// 
        for(int i=0;i<arrayOfLinkedList.array.length;i++)
        { noOfBlackPixel[i]=0;
          k1=this.arrayOfLinkedList.array[i].head;  
          k2=Imgarray.array[i].head;
          l1=k1.next;
          l2=k2.next;
          if(k1.element==-1 || k2.element ==-1)
          {
             value1 =new Node(-1,-1);
             OrArray.array[i].add(value1);
          } 
          while(k1.element!=-1 && k2.element!=-1)
          {
             //All the possible cases
             //System.out.println(k2.element); 
             if(k1.element<=k2.element && l2.element<=l1.element)
             {
                value1=new Node(k2.element,0);
                value2=new Node(l2.element,1);
                OrArray.array[i].add(value1);
                OrArray.array[i].add(value2);
                noOfBlackPixel[i]+=value2.element-value1.element+1;
             }   
             else if(k2.element<=k1.element && l1.element<=l2.element) 
             { 
                value1=new Node(k1.element,0);
                value2=new Node(l1.element,1);
                OrArray.array[i].add(value1);
                OrArray.array[i].add(value2);
                noOfBlackPixel[i]+=value2.element-value1.element+1;
              
             }
             else if(k1.element<=k2.element && l1.element<l2.element && k2.element<=l1.element)
             {
              value1=new Node(k2.element,0);
              value2 =new Node(l1.element,1);
              OrArray.array[i].add(value1);
              OrArray.array[i].add(value2);
              noOfBlackPixel[i]+=value2.element-value1.element+1;
             }
             else if(k2.element<k1.element && l2.element<=l1.element && k1.element<=l2.element)
             {
               value1=new Node(k1.element,0);
               value2 =new Node(l2.element,1);
               OrArray.array[i].add(value1);
               OrArray.array[i].add(value2);
               noOfBlackPixel[i]+=value2.element-value1.element+1;
             }  
             // deciding the next iteration
             if(k1.element<=k2.element && l2.element<=l1.element)
             {
              k2=l2.next;
              l2=k2.next;
             }  
             else if(k2.element<=k1.element && l1.element<=l2.element)
             {
              k1=l1.next;
              l1=k1.next;
             } 
             else if(k1.element<=k2.element && l1.element<l2.element && k2.element<=l1.element)
             {
              k1=l1.next;
              l1=k1.next;
             }  
             else if(k2.element<k1.element && l2.element<=l1.element && k1.element<=l2.element)
             {
              k2=l2.next;
              l2=k2.next;
             }
             else if(l1.element<k2.element)
             {
                k1=l1.next;
                l1=k1.next;
             }  
             else if(l2.element<k1.element)
             {
              k2=l2.next;
              l2=k2.next;
             }  
             if(k1.element==-1 || k2.element==-1)
              { //System.out.println("I am here");
                value1= new Node(-1,-1);
                OrArray.array[i].add(value1);
              } 

          } 

        }
        this.arrayOfLinkedList=OrArray;
		 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }
    
    public void performXor(CompressedImageInterface img) throws BoundsMismatchException
    {
		  //you need to implement this
      CompressedImageInterface A=new LinkedListImage(this);
      CompressedImageInterface B=new LinkedListImage(img);
      try
        {
          this.performOr(img);//this=A+B    
        }
        catch (BoundsMismatchException e)
        {
             throw new BoundsMismatchException("Images of different size");
        }  
       A.invert(); //A=not(A)
       B.invert();//B=not(B)

         try
        {
           A.performOr(B);//A=not(A)+not(B)     
        }
        catch (BoundsMismatchException e)
        {
            throw new BoundsMismatchException("Images of different size");
        }
        	try
        {
          this.performAnd(A);//(A+B).(not(A)+not(B))     
        }
        catch (BoundsMismatchException e)
        {
            throw new BoundsMismatchException("Images of different size");
        }
      //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }
    
    public String toStringUnCompressed()
    {
		 //you need to implement this
     String s="";
     Node k,l;
     s=Dimensions[0]+" "+Dimensions[1]+", ";
     for(int i=0;i<arrayOfLinkedList.array.length;i++)
     { k=arrayOfLinkedList.array[i].head;
       l=k.next;
       for(int j=0;j<Dimensions[1];j++)
       {   
           if(k.knowWhat==-1)
           {
             if(j==Dimensions[1]-1)
              s+="1, ";
             else 
              s+="1 ";
           }
           if(k.knowWhat==0 && j<k.element)
           {  
               s+="1 ";
           }
           else if (k.knowWhat==0 && j>=k.element && j<=l.element)
           { if(j==Dimensions[1]-1)
              s+="0, ";
             else 
              s+="0 "; 
             if(j==l.element)
              k=l.next;
              l=k.next;

           } 
            
       }
     }
    
		 //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    s=s.replaceAll(", $","");//Learn more About it
    return s;
    }
    
    public String toStringCompressed()
    {
		//you need to implement this
		String s="";
	    Node k;
		s=s+Dimensions[0]+" "+Dimensions[1]+", ";
		for(int i=0;i<arrayOfLinkedList.array.length;i++)
		{  k=arrayOfLinkedList.array[i].head;
           while(k.knowWhat!=-1)
            {  
              s=s+k.element+" ";
              k=k.next;
            }
           if(i!=arrayOfLinkedList.array.length-1)
            s=s+"-1, ";
           else
           	s=s+"-1";
		}
		//System.out.println(s);
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
		return s;
    }
    //MY function I am the author of this.
    public void toCompressed(int [][] mygrid)
      {  boolean entering=false;
      	 Node value;
         noOfBlackPixel=new int[mygrid.length];
         int index=0;
         for(int l=0;l<mygrid.length;l++)
           noOfBlackPixel[l]=0;
      	 //System.out.println(mygrid.length);
         arrayOfLinkedList=new MyArrayList(mygrid.length);
         for (int i=0;i<mygrid.length;i++)
          { for(int j=0;j<mygrid[0].length;j++)
         	  {
         	    if (mygrid[i][j]==0 && j==mygrid[0].length-1 && entering==true) 
                 {  value=new Node();
                 	value.element=j;
                 	value.knowWhat=1;
                 	entering=false;
                 	arrayOfLinkedList.array[i].add(value);
                    noOfBlackPixel[i]+=j-index+1;

                 }
                else if(mygrid[i][j]==0 && j==mygrid[0].length-1 && entering==false)
                 {  
                 	value=new Node();
                 	value.element=j;
                 	value.knowWhat=0;//special case when a single the last element is black eg:001001110
                 	entering=false;
                 	arrayOfLinkedList.array[i].add(value);
                 	value=new Node();
                 	value.element=j;
                 	value.knowWhat=1;
                 	arrayOfLinkedList.array[i].add(value);
                    noOfBlackPixel[i]+=1;                
                 }
         	      else if(mygrid[i][j]==0 && entering==false)
         	 	    {  
                   index=j;
                   value=new Node(); 
         	 	       entering=true;
         	  	 	   value.element=j;
         	 	  	   value.knowWhat=0;
         	 	  	   arrayOfLinkedList.array[i].add(value);
         	      }
                 else if(mygrid[i][j]==1 && entering==true)
                 {
                    noOfBlackPixel[i]+=j-index;
                    value=new Node();
                 	value.element=j-1;
                 	value.knowWhat=1;
                 	arrayOfLinkedList.array[i].add(value);
                	entering=false;
 
                 }	
              }
            value=new Node();
            value.element=-1;
            value.knowWhat=-1;
            //System.out.println(i);
            //System.out.println(arrayOfLinkedList.array.length);
            arrayOfLinkedList.array[i].add(value);

          }
      }

    public static void main(String[] args) {
    	// testing all methods here :
      boolean success = true;

      // check constructor from file
      CompressedImageInterface img1 = new LinkedListImage("sampleInputFile.txt");

      // check toStringCompressed
      String img1_compressed = img1.toStringCompressed();
      String img_ans = "16 16, -1, 5 7 -1, 3 7 -1, 2 7 -1, 2 2 6 7 -1, 6 7 -1, 6 7 -1, 4 6 -1, 2 4 -1, 2 3 14 15 -1, 2 2 13 15 -1, 11 13 -1, 11 12 -1, 10 11 -1, 9 10 -1, 7 9 -1";
      success = success && (img_ans.equals(img1_compressed));

      if (!success)
      {
        System.out.println("Constructor (file) or toStringCompressed ERROR");
        return;
      }

      // check getPixelValue
      boolean[][] grid = new boolean[16][16];
      for (int i = 0; i < 16; i++)
        for (int j = 0; j < 16; j++)
        {
                try
                {
              grid[i][j] = img1.getPixelValue(i, j);                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
        }

      // check constructor from grid
      CompressedImageInterface img2 = new LinkedListImage(grid, 16, 16);
      String img2_compressed = img2.toStringCompressed();
      success = success && (img2_compressed.equals(img_ans));

      if (!success)
      {
        System.out.println("Constructor (array) or toStringCompressed ERROR");
        return;
      }

      // check Xor
        try
        {
          img1.performXor(img2);       
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
      for (int i = 0; i < 16; i++)
        for (int j = 0; j < 16; j++)
        {
                try
                {
              success = success && (!img1.getPixelValue(i,j));                
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
        }

      if (!success)
      {
        System.out.println("performXor or getPixelValue ERROR");
        return;
      }

      // check setPixelValue
      for (int i = 0; i < 16; i++)
        {
            try
            {
            img1.setPixelValue(i, 0, true);            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }

      // check numberOfBlackPixels
      int[] img1_black = img1.numberOfBlackPixels();
      success = success && (img1_black.length == 16);
      for (int i = 0; i < 16 && success; i++)
        success = success && (img1_black[i] == 15);
      if (!success)
      {
        System.out.println("setPixelValue or numberOfBlackPixels ERROR");
        return;
      }

      // check invert
        img1.invert();
        for (int i = 0; i < 16; i++)
        {
            try
            {
                success = success && !(img1.getPixelValue(i, 0));            
            }
            catch (PixelOutOfBoundException e)
            {
                System.out.println("Errorrrrrrrr");
            }
        }
        if (!success)
        {
            System.out.println("invert or getPixelValue ERROR");
            return;
        }

      // check Or
        try
        {
            img1.performOr(img2);        
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && img1.getPixelValue(i,j);
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performOr or getPixelValue ERROR");
            return;
        }

        // check And
        try
        {
            img1.performAnd(img2);    
        }
        catch (BoundsMismatchException e)
        {
            System.out.println("Errorrrrrrrr");
        }
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
            {
                try
                {
                    success = success && (img1.getPixelValue(i,j) == img2.getPixelValue(i,j));             
                }
                catch (PixelOutOfBoundException e)
                {
                    System.out.println("Errorrrrrrrr");
                }
            }
        if (!success)
        {
            System.out.println("performAnd or getPixelValue ERROR");
            return;
        }

      // check toStringUnCompressed
        String img_ans_uncomp = "16 16, 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1, 1 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1, 1 1 1 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 0 0 0 0 0 1 1 1 1 1 1 1 1, 1 1 0 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 1 1 0 0 1 1 1 1 1 1 1 1, 1 1 1 1 0 0 0 1 1 1 1 1 1 1 1 1, 1 1 0 0 0 1 1 1 1 1 1 1 1 1 1 1, 1 1 0 0 1 1 1 1 1 1 1 1 1 1 0 0, 1 1 0 1 1 1 1 1 1 1 1 1 1 0 0 0, 1 1 1 1 1 1 1 1 1 1 1 0 0 0 1 1, 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1, 1 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1, 1 1 1 1 1 1 1 1 1 0 0 1 1 1 1 1, 1 1 1 1 1 1 1 0 0 0 1 1 1 1 1 1";
        success = success && (img1.toStringUnCompressed().equals(img_ans_uncomp)) && (img2.toStringUnCompressed().equals(img_ans_uncomp));//changed

        if (!success)
        {
            System.out.println("toStringUnCompressed ERROR");
            return;
        }
        else
            System.out.println("ALL TESTS SUCCESSFUL! YAYY!");
    }
}
//MyNode Class
class Node
{
	public int element;
    public int knowWhat;
	public Node next; 
	public Node(int element,int knowWhat)
	{
		this.element=element;
		this.knowWhat=knowWhat;
		next=null;

	}
	public Node()
	{next=null;
	}

}
class MyLinkedList{
	Node head;
	Node tail;
	int size;
	public MyLinkedList()
	{
		head=null;
		tail=null;
		size=0;
	}

	public void add(Node node)
	{
      if(size==0)
      {
          head=node;
          tail=node;
          size++;
      }
      else
      {
      	  tail.next=node;
      	  tail=node;
      	  size++;
      }
	}
}
class MyArrayList
{   MyLinkedList[] array;
	public MyArrayList(int size)
	{
        array=new MyLinkedList[size];
        for(int i=0;i<size;i++)
        	array[i]=new MyLinkedList();
	}
}