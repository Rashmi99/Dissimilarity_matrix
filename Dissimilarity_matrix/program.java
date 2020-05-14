import java.util.ArrayList;
import java.util.Scanner;

public class Dissimilarity_2 {

	private static Scanner key;
	public static void main(String args[]) {
		try {
			key = new Scanner(System.in);
			ArrayList<Integer> n_al=new ArrayList<Integer>(20);
			ArrayList<Float> o_al=new ArrayList<Float>(20);
			ArrayList<Float> num_al=new ArrayList<Float>(20);			
			ArrayList<Float> sym_bin_al=new ArrayList<Float>(20);
			ArrayList<Float> asym_bin_al=new ArrayList<Float>(20);
			int p=0,q=0,r=0,s=0;
			
			System.out.println("For mixed type");
			System.out.println("Enter the number of objects");
			int n_obj=key.nextInt();
			System.out.println("Enter number of columns");
			int cols=key.nextInt();			
		
			String[][] attributes=new String[n_obj+1][cols];  //create a table for all kinds of attributes
		
			System.out.println("Enter the table for mixed type:");
			for(int i=0;i<=n_obj;i++) {
				for(int j=0;j<cols;j++) {
					attributes[i][j]=key.next();
				}
			}
						
			System.out.println("For binary attributes");
			System.out.println("Enter the number of rows");
			int bin_obj=key.nextInt();
			System.out.println("Enter number of columns");
			int bin_cols=key.nextInt();
			int[][] bin_attributes=new int[bin_obj][bin_cols];
			System.out.println("Enter the values: 1 for YES and 0 for NO");
			for(int i=0;i<bin_obj;i++) {
				for(int j=0;j<bin_cols;j++) {
					bin_attributes[i][j]=key.nextInt();
				}
			}
	
			//printing the values
			System.out.println("The mixed columns are");
			for(int i=0;i<=n_obj;i++) {
				for(int j=0;j<cols;j++) {
					System.out.print(attributes[i][j]+"\t");
				}
				System.out.print("\n");
			}
			System.out.println("The binary columns are");
			for(int i=0;i<bin_obj;i++) {
				for(int j=0;j<bin_cols;j++) {
					System.out.print(bin_attributes[i][j]+"\t");
				}
				System.out.print("\n");
			}
			
			//calculation for mixed type
			for(int k=0;k<cols;k++) {
				//nominal
				if(attributes[0][k].equalsIgnoreCase("nominal")) {
					p=1;
					System.out.println("The dissimilarity matrix for nominal type is");
					for(int i=1;i<n_obj;i++) {
						for(int j=i+1;j<=n_obj;j++) {
							if(attributes[i][k].equals(attributes[j][k]))
								n_al.add(0);
							else
								n_al.add(1);
							}					
						}
					int x=0;
					for(int i=0;i<n_obj;i++) {
						for(int j=0;j<=i;j++) {
							if(i==j)System.out.println(0);
							else {
								if(x<n_al.size()) System.out.print(n_al.get(x++)+"\t");
								}
							}
						System.out.print("\n");
						}					
					}
				
				//ordinal
				else if(attributes[0][k].equalsIgnoreCase("ordinal")) {
					q=1;
					System.out.println("The dissimilarity matrix for ordinal type is");
					float[] temp=new float[n_obj+1];
					for(int i=1;i<=n_obj;i++) {
						temp[i]=getval(attributes,n_obj,i,k);
						}					
					for(int i=1;i<temp.length;i++) {
						for(int j=i+1;j<temp.length;j++) {
							o_al.add(Math.abs((float)temp[i]-(float)temp[j]));
							}
						}
					printvals(o_al,n_obj);
					}
				
				//numeric
				else if(attributes[0][k].equalsIgnoreCase("numeric")) {
					r=1;
					System.out.println("The dissimilarity matrix for numeric type is");
					int max=getmax(attributes,n_obj,k);
					int min=getmin(attributes,n_obj,k);
	
					for(int i=1;i<n_obj;i++) {
						for(int j=i+1;j<=n_obj;j++) {
							num_al.add(Math.abs(Float.parseFloat(attributes[i][k])-Float.parseFloat(attributes[j][k]))/(max-min));						
							}					
						}
					printvals(num_al,n_obj);
					}					
				}
			
			//mixed attributes
			System.out.println("The dissimilarity matrix for mixed type is");
			if(p==1 && q==1 && r==1) {
				s=0;
				for(int i=0;i<n_al.size();i++) {
					for(int j=0;j<=i;j++) {
						if(i==j && i<=cols)System.out.println(0);
						else{
							if(s<n_al.size())System.out.print((n_al.get(s)+num_al.get(s)+o_al.get(s))/cols+"\t");
							s++;
							}							
						}
					System.out.print("\n");
					}				
				}
			else if(p==1 && q==1 && r==0) {
				s=0;
				for(int i=0;i<n_al.size();i++) {
					for(int j=0;j<=i;j++) {
						if(i==j && i<=cols)System.out.println(0);
						else{
							if(s<n_al.size())System.out.print((n_al.get(s)+o_al.get(s))/cols+"\t");
							s++;
						}					
					}
				System.out.print("\n");
				}
			}
			else if(p==1 && q==0 && r==1) {
				s=0;
				for(int i=0;i<n_al.size();i++) {
					for(int j=0;j<=i;j++) {
						if(i==j && i<=cols)System.out.println(0);
						else {
							if(s<n_al.size())System.out.print((n_al.get(s)+num_al.get(s))/cols+"\t");
							s++;
						}					
					}
				System.out.print("\n");
				}
			}
			else if(p==0 && q==1 && r==1) {
				s=0;
				for(int i=0;i<o_al.size();i++) {
					for(int j=0;j<=i;j++) {
						if(i==j && i<=cols)System.out.println(0);
						else {
							if(s<o_al.size())System.out.print((o_al.get(s)+num_al.get(s))/cols+"\t");
							s++;
						}					
					}
				System.out.print("\n");
				}
			}	
			
			//binary attributes:
			p=0;q=0;r=0;s=0;
			for(int i=0;i<bin_obj;i++) {
				for(int j=i+1;j<bin_obj;j++) {
					p=0;q=0;r=0;s=0;
					for(int k=0;k<bin_cols;k++) {
						if(bin_attributes[i][k]==1 && bin_attributes[j][k]==1) p+=1;
						else if(bin_attributes[i][k]==1 && bin_attributes[j][k]==0) q+=1;
						else if(bin_attributes[i][k]==0 && bin_attributes[j][k]==1) r+=1;
						else if(bin_attributes[i][k]==0 && bin_attributes[j][k]==0) s+=1;
						else System.out.println("Invalid input");					
					}
					sym_bin_al.add((float)(q+r)/(p+q+r+s));
					asym_bin_al.add((float)(q+r)/(p+q+r));
				}							
			}
			System.out.println("The dissimilarity matrix for Symmetric binary attributes is:");
			printvals(sym_bin_al,bin_obj);
			System.out.println("The dissimilarity matrix for Asymmetric binary attributes is:");
			printvals(asym_bin_al,bin_obj);
					
		}
		
		catch(Exception e) {System.out.println(e);}
	}
	
	public static int getmax(String a[][],int n,int k) {
		int max=Integer.parseInt(a[1][k]);
		for(int i=1;i<=n;i++) {
			if(Integer.parseInt(a[i][k])>max) max=Integer.parseInt(a[i][k]); 
		}
		return max;							
	}
	public static int getmin(String a[][],int n,int k) {
		int min=Integer.parseInt(a[1][k]);
		for(int i=1;i<=n;i++) {
			if(Integer.parseInt(a[i][k])<min) min=Integer.parseInt(a[i][k]);
		}
		return min;							
	}
	public static float getval(String a[][],int n,int i,int k) {
		float val=0;
		if(a[i][k].equalsIgnoreCase("excellent") || a[i][k].equalsIgnoreCase("best") || a[i][k].equalsIgnoreCase("happy"))	val=1;
		else if(a[i][k].equalsIgnoreCase("good") || a[i][k].equalsIgnoreCase("normal")) val=(float)0.50;
		return val;
	}
	public static void printvals(ArrayList<Float>a,int n) {
		int k=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<=i;j++) {
				if(i==j)System.out.println(0);
				else {
					if(k<a.size()) System.out.print(a.get(k++)+"\t");
				}
			}
			System.out.print("\n");
		}		
	}	
	
}
