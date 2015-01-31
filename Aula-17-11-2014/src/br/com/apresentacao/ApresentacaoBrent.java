package br.com.apresentacao;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import aluno.Aluno;
import organizador.OrganizadorBrent;

public class ApresentacaoBrent {
	public static void main(String[] args) {
		try {
			// Organizador
			OrganizadorBrent organizadorBrent = 
					new OrganizadorBrent(GerarArquivo.NM_ARQUIVO_APRESENTACAO);
			
			// Inseri na sequencia do slide passado em sala
			/*organizadorBrent.addReg(new Aluno(27, "Aluno", "Rua 10",
					(short) 18, "M", "aluno@ufs.br"));

			organizadorBrent.addReg(new Aluno(18, "Aluno", "Rua 10",
					(short) 18, "M", "aluno@ufs.br"));
			
			organizadorBrent.addReg(new Aluno(29, "Aluno "+29, "Rua",
					(short) 18, "M", "mail"));*/
			
			
			// varre o arquivo e mostra as posições
			File fOrigem = new File(GerarArquivo.NM_ARQUIVO_APRESENTACAO);
			RandomAccessFile randomAccessFile = new RandomAccessFile(fOrigem, "r");
			FileChannel canal = randomAccessFile.getChannel();
			
			for (long pos=0; pos < canal.size(); pos +=Aluno.LENGTH)  {
				// Ler da origem
				ByteBuffer buff = ByteBuffer.allocate(Aluno.LENGTH);
				canal.read(buff, pos);

				buff.flip();
				Aluno aluno = new Aluno(buff);

				System.out.println("Posição: "+ (pos/Aluno.LENGTH)+ " - Matricula: "+ aluno.getMatricula());
			}
			
			canal.close();
			randomAccessFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
