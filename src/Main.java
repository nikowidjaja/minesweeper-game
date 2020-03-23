import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Main implements MouseListener {
	// Component untuk game screen
	JFrame frame = new JFrame("MineSweeper");
	JPanel panel = new JPanel();
	JToggleButton[][] blocks;
	int isRecursed;
	int nFlags = 0;
	int score;
	int openBlocks; // untuk mengetahui jumlah block yg udah dibuka
	int[][] flags; // jika 0 berarti sudah ada flag
	int[][] visited; // jika 0 berarti udah pernah visit
	int[][] value; // jika 0 berarti tidak ada bomb disekitar
	boolean found;

	// Component untuk menu
	JFrame menuFrame = new JFrame();
	JPanel menuPanel = new JPanel();
	JLabel title = new JLabel("Minesweeper");
	JLabel titleImg = new JLabel(new ImageIcon("mine.png"));

	JLabel enter = new JLabel("");

	int nRows, nColumns, nBombs, bombNearBy, x, y;
	Scanner scan = new Scanner(System.in);

	// Method InitValue untuk insialisasi array 2 dimensi seperti
	// Flags -> untuk menyimpan posisi flag
	// visited -> untuk menyimpan block yang telah divisit , agar saat recursive
	// tidak terjadi pengulangan
	// Value -> untuk menyimpan lokasi bomb dan juga value dari setiap blocks
	public void initValue(int nRows, int nColumns) {

		// INISIALISASI VALUE AWAL
		for (int[] row : value) {
			Arrays.fill(row, 0);
		}
		for (int[] row : visited) {
			Arrays.fill(row, -1);
		}
		for (int[] row : flags) {
			Arrays.fill(row, -1);
		}

		// INISIALISASI POSISI BOMB
		for (int i = 0; i < nBombs; i++) {
			do {
				x = (int) (Math.random() * nColumns);
				y = (int) (Math.random() * nRows);
			} while (value[x][y] == -1);
			value[x][y] = -1;
		}

		// Untuk mencari value dari setiap block
		// Value setiap block ditentukan berdasarkan berapa banyak bom yang ada
		// disekitar.
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				System.out.println("Matrix : " + i + " " + j);
				bombNearBy = 0;
				if (value[i][j] != -1) {
					// topleft
					if (i != 0 && j != 0) {
						if (value[i - 1][j - 1] == -1)
							bombNearBy++;
					}
					// top
					if (i != 0) {
						if (value[i - 1][j] == -1)
							bombNearBy++;
					}

					// topright
					if (i != 0 && j != nColumns - 1) {
						if (value[i - 1][j + 1] == -1)
							bombNearBy++;
					}

					// right
					if (j != nColumns - 1) {
						if (value[i][j + 1] == -1)
							bombNearBy++;
					}
					// left
					if (j != 0) {
						if (value[i][j - 1] == -1)
							bombNearBy++;
					}

					// bottomleft
					if (i != nRows - 1 && j != 0) {
						if (value[i + 1][j - 1] == -1)
							bombNearBy++;
					}

					// bottom
					if (i != nRows - 1) {
						if (value[i + 1][j] == -1)
							bombNearBy++;
					}

					// bottomright
					if (i != nRows - 1 && j != nColumns - 1) {
						if (value[i + 1][j + 1] == -1)
							bombNearBy++;
					}
					value[i][j] = bombNearBy;
					System.out.println(value[i][j]);
				}
			}
		}

		// Array 2d Value
		for (int row = 0; row < nRows; row++) {
			for (int column = 0; column < nColumns; column++) {
				System.out.print(value[row][column] + " ");
			}
			System.out.println();
		}

	}

	public Main() {
		isRecursed = 0; // untuk mengetahui apakah sudah recursive, digunakan pada saat pindah dari game
						// screen ke menu
		score = 0; // menyimpan jumlah step
		openBlocks = 0; // mengetahui seberapa banyak block yang telah dibuka , digunakan dalam
						// menentukan menang kalah

		JButton easyDiff, mediumDiff, hardDiff, customDiff;

		easyDiff = new JButton("Easy") {
			{
				setFont(new Font("Tahoma", 1, 20));
				setBackground(Color.WHITE);
			}
		};

		mediumDiff = new JButton("Medium") {
			{
				setFont(new Font("Tahoma", 1, 20));
				setBackground(Color.WHITE);
			}
		};

		hardDiff = new JButton("Hard") {
			{
				setFont(new Font("Tahoma", 1, 20));
				setBackground(Color.WHITE);
			}
		};

		customDiff = new JButton("Custom") {
			{
				setFont(new Font("Tahoma", 1, 20));
				setBackground(Color.WHITE);
			}
		};

		title.setFont(new Font("Tahoma", 1, 30));

		easyDiff.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				setComponent(1, 0);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		mediumDiff.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				setComponent(2, 0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		hardDiff.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				setComponent(3, 0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		customDiff.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				// JFrame customDiffFrame = new JFrame();
				JDialog inputDiff = new JDialog();
				String customRow;
				int customRowInt;

				inputDiff.setVisible(true);
				inputDiff.setLocationRelativeTo(null);

				customRow = JOptionPane.showInputDialog(inputDiff, "Input row size: ");
				customRowInt = Integer.parseInt(customRow);
				// System.out.println(customRowInt);

//				customDiffFrame.add(inputDiff);
//				customDiffFrame.setVisible(true);
//				customDiffFrame.setLocationRelativeTo(null);

				setComponent(4, customRowInt);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		menuPanel.add(title);
		menuPanel.add(titleImg);

		menuPanel.add(easyDiff);
		menuPanel.add(mediumDiff);
		menuPanel.add(hardDiff);
		menuPanel.add(customDiff);

		menuFrame.setSize(500, 600);
		menuFrame.setLocationRelativeTo(null);

		menuFrame.add(menuPanel);
		menuFrame.setVisible(true);

	}

	// doRecursive bertujuan untuk membuka blocks saat diklik
	public void doRecursive(int btnRow, int btnCol) {
		// Pertama akan dicek apakah yang diklik itu bomb atau bukan
		// -1 -> menandakan bomb
		// Jika bomb akan game over
		if (value[btnRow][btnCol] == -1) {
			System.out.println("pass");
			for (int i = 0; i < nRows; i++) {
				for (int j = 0; j < nColumns; j++) {
					if (value[i][j] == -1) {
						blocks[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
						blocks[i][j].setForeground(Color.red);
						JLabel bombImg = new JLabel(new ImageIcon("bombb-removebg-preview.png"));
						blocks[i][j].add(bombImg);
						blocks[i][j].setSelected(true);
					}
				}
			}
			JLabel label = new JLabel("<html>" + "Sorry you lose :(" + "<br>" + "Your Score Is  " + score + "<br>"
					+ " Do you want to retry?" + "</html>");
			label.setFont(new Font("Arial", Font.BOLD, 18));

			// validasi isRecursed bertujuan agar JOptionPane tidak pop up 2 kali
			if (isRecursed == 0) {
				int dialogResult = JOptionPane.showConfirmDialog(null, label, "You Lose", JOptionPane.YES_NO_OPTION);
				if (dialogResult == 0) {
					isRecursed = 1;
					// HERE
					frame.dispose();
					new Main();
				} else {
					System.exit(0);
				}
			}
		}

		// Jika block yang diklik bukan bomb
		else {
			// Pertama akan block tersebut akan setSelected(true) biar tidak diklik dan pop
			// up lagi
			blocks[btnRow][btnCol].setSelected(true);

			// Di cek apakah block tersebut sudah divisit
			if (visited[btnRow][btnCol] == -1) {
				// Jika belum divisit maka akan diubah menjadi visited
				visited[btnRow][btnCol] = 0;
				// selanjutnya jumlah block yang terbuka akan ditambah 1
				openBlocks++;

				// Jika value dari block tersebut tidak sama dengan nol maka
				// tidak lakukan recursive lagi,hanya tampilkan valuenya saja
				if (value[btnRow][btnCol] != 0) {
					blocks[btnRow][btnCol].setFont(new Font("Arial", Font.PLAIN, 40));
					blocks[btnRow][btnCol].setForeground(Color.black);
					blocks[btnRow][btnCol].setText(Integer.toString(value[btnRow][btnCol]));
				}

				// Jika value dari block tersebut adalah nol
				// Maka akan dilakukan recursive ke 4 arah untuk mengecek sampai ketemu yang
				// tidak nol
				else if (value[btnRow][btnCol] == 0) {
					// Check Top
					blocks[btnRow][btnCol].setText("");
					if (btnRow != 0 && btnCol != 0 || btnRow != 0 && btnCol != nColumns - 1) {
						if (value[btnRow - 1][btnCol] == 0) {
							blocks[btnRow - 1][btnCol].setSelected(true);
						}
						doRecursive(btnRow - 1, btnCol);
					}

					// Check bottom
					if (btnRow != nRows - 1 && btnCol != 0 || btnRow != nRows - 1 && btnCol != nColumns - 1) {
						if (value[btnRow + 1][btnCol] == 0) {
							blocks[btnRow + 1][btnCol].setSelected(true);
						}
						doRecursive(btnRow + 1, btnCol);
					}

					// Check left
					if (btnCol != 0) {
						if (value[btnRow][btnCol - 1] == 0) {
							blocks[btnRow][btnCol - 1].setSelected(true);
						}
						doRecursive(btnRow, btnCol - 1);
					}
					// Check right
					if (btnCol != nColumns - 1) {
						if (value[btnRow][btnCol + 1] == 0) {
							blocks[btnRow][btnCol + 1].setSelected(true);
						}
						doRecursive(btnRow, btnCol + 1);
					}
				}
			}
		}
	}

	// BUAT KASIH FLAG
	public void mouseClicked(MouseEvent e) {
		// CHECK BUTTON MANA YANG DIKLIK
		int btnRow = 0, btnCol = 0;
		found = false;
		for (btnRow = 0; btnRow < nRows; btnRow++) {
			for (btnCol = 0; btnCol < nColumns; btnCol++) {
				if (e.getSource() == blocks[btnRow][btnCol]) {
					found = true;
					break;
				}
			}
			if (found)
				break;
		}

		// Jika button mouse kanan diklik
		if (e.getButton() == MouseEvent.BUTTON3 && visited[btnRow][btnCol] != 0) {
			blocks[btnRow][btnCol].setFont(new Font("Arial", Font.PLAIN, 40));
			blocks[btnRow][btnCol].setForeground(Color.green);
			if (nFlags <= nRows) {
				// Check apakah sudah udah ada flag atau belum
				if (flags[btnRow][btnCol] == -1) { // belum ada flag
					blocks[btnRow][btnCol].setText("F");
					flags[btnRow][btnCol] = 0;
					nFlags++;
				} else { // Sudah ada Flag
					blocks[btnRow][btnCol].setText(" ");
					flags[btnRow][btnCol] = -1;
					nFlags--;
				}

			}

			// Jika jumlah flag sudah lebih maka tidak dapat add flag lagi
			if (nFlags > nRows) {
				blocks[btnRow][btnCol].setText(" ");
				flags[btnRow][btnCol] = -1;
				nFlags--;
			}
		}

		// Jika klik mouse kiri
		else {

			// akan lakukan recursive dulu baru cek status menang atau tidak
			doRecursive(btnRow, btnCol);

			// Kondisi menang ketika, jumlah openBlocks sama dengan jumlah blocks - jumlah
			// bomb
			if (openBlocks == (nRows * nColumns) - nBombs) {
				JLabel label = new JLabel("<html>" + "You Win" + "<br>" + "Your steps are " + score + "<br>"
						+ " Do you want to retry?" + "</html>");
				label.setFont(new Font("Arial", Font.BOLD, 18));
				int dialogResult = JOptionPane.showConfirmDialog(null, label, "You Win", JOptionPane.YES_NO_OPTION);
				if (dialogResult == 0) {
					frame.dispose();
					new Main();
				} else {
					System.exit(0);
				}
			}
//			else{
//				doRecursive(btnRow,btnCol);
//			}

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Score ditambah disini karena jika ditaruh di mouseclick akan tambah 2 kali
		// Hal ini disebabkan karena ada panggil mouseClick dibawah
		score++;

		// MouseClicked dipanggil disini agar saat dipress tidak bug
		mouseClicked(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked(e);
	}

	public void setComponent(int diff, int customRowInt) {
		// Setiap kali ke gameScreen, menu sebelumnya bakal dimatikan
		menuFrame.setVisible(false);
		if (diff == 1)
			nRows = 7;
		else if (diff == 2)
			nRows = 10;
		else if (diff == 3)
			nRows = 15;
		else if (diff == 4) {
			nRows = customRowInt;
		}

		nColumns = nRows;
		nBombs = nRows;
		panel.setLayout(new GridLayout(nRows, nColumns, 0, 0));

		value = new int[nRows][nColumns];
		visited = new int[nRows][nColumns];
		blocks = new JToggleButton[nRows][nColumns];
		flags = new int[nRows][nColumns];

		initValue(nRows, nColumns);

		// Untuk membuat 2d Array ToggleButton sesuai dengan ukuran yang dimasukkan
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				blocks[i][j] = new JToggleButton();
				blocks[i][j].addMouseListener(this);
				panel.add(blocks[i][j]);
			}
		}

		frame.add(panel);
		frame.setSize(nRows * 100, nColumns * 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
