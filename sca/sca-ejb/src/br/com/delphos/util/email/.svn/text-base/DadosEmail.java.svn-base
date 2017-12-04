package br.com.delphos.util.email;

import java.io.*;
import javax.mail.internet.MimeUtility;

public class DadosEmail {
    
    private TipoDados tipoDados = TipoDados.TEXTO;
    private String codificacao = null;
    private String codificacaoOriginal = null;
    private String mimeType = null;
    private String dados = null;
    private String nome = null;
    private File arquivo = null;

    public DadosEmail() {}

    public DadosEmail(String codificacao) {
        this.codificacao = codificacao;
    }

    public static DadosEmail arquivoTexto(File arquivo, String... args) {
        DadosEmail ret = new DadosEmail();
        ret.setArquivo(arquivo);
        ret.setTipoDados(TipoDados.TEXTO);
        ret.setMimeType("text/plain");
        if (args.length > 0) {
            ret.setCodificacao(args[0]);
        }
        if (args.length > 1) {
            ret.setCodificacaoOriginal(args[1]);
        }
        if (args.length > 2) {
            ret.setMimeType(args[2]);
        }
        return ret;
    }
    
    public static DadosEmail arquivoBinario(File arquivo, String... args) {
        DadosEmail ret = new DadosEmail();
        ret.setArquivo(arquivo);
        ret.setCodificacao("");
        ret.setTipoDados(TipoDados.BINARIO);
        ret.setMimeType("application/octet-stream");
        if (args.length > 0) {
            ret.setCodificacao(args[0]);
        }
        if (args.length > 1) {
            ret.setMimeType(args[1]);
        }
        return ret;
    }
    
    public static DadosEmail campo(String valor, String... args) {
        DadosEmail ret = new DadosEmail();
        ret.setDados(valor);
        ret.setCodificacao("");
        ret.setTipoDados(TipoDados.TEXTO);
        ret.setMimeType(null);
        if (args.length > 0) {
            ret.setCodificacao(args[0]);
        }
        if (args.length > 1) {
            ret.setMimeType(args[1]);
        }
        return ret;
    }
    
    public static DadosEmail conteudoTexto(String valor, String... args) {
        DadosEmail ret = new DadosEmail();
        ret.setDados(valor);
        ret.setTipoDados(TipoDados.TEXTO);
        ret.setCodificacao("");
        ret.setMimeType("text/plain");
        if (args.length > 0) {
            ret.setCodificacao(args[0]);
        }
        if (args.length > 1) {
            ret.setMimeType(args[1]);
        }
        return ret;
    }
    
    public static DadosEmail conteudoHtml(String valor, String... args) {
        DadosEmail ret = new DadosEmail();
        ret.setDados(valor);
        ret.setTipoDados(TipoDados.TEXTO);
        ret.setMimeType("text/html");
        if (args.length > 0) {
            ret.setCodificacao(args[0]);
        }
        if (args.length > 1) {
            ret.setMimeType(args[1]);
        }
        return ret;
    }
    
    public boolean isArquivo() {
        return arquivo != null;
    }
    
    public boolean isCampo() {
        return dados != null;
    }
    
    public boolean isValido() {
        return isArquivo() || isCampo();
    }
    
    public String gerarMimeType() {
        return mimeType != null ? (mimeType + (codificacao != null ? "; charset=" + codificacao : "")) : "application/octet-stream";
    }

    public String gerarCodificacao() {
        return codificacao == null ? ConstantesEmail.getCodificacaoJvmPadrao() : codificacao.isEmpty() ? ConstantesEmail.CodificacaoEnvio : codificacao;
    }
    
    public String gerarDadosCampo() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        if (dados != null) {
            if (codificacao == null) {
                return MimeUtility.encodeText(dados);
            } else if (codificacao.isEmpty()) {
                return MimeUtility.encodeText(dados, ConstantesEmail.getCodificacaoJvmPadrao(), ConstantesEmail.getCodificacaoRfc822Padrao());
            } else {
                return MimeUtility.encodeText(dados, codificacao, null);
            }
        } else if (arquivo != null) {
            String conteudo;
            String codificacaoLeitura = codificacaoOriginal == null ? codificacao : codificacaoOriginal;
            if (codificacaoLeitura == null) {
                conteudo = EmailUtils.readTextFile(arquivo);
            } else if (codificacaoLeitura.isEmpty()) {
                conteudo = EmailUtils.readTextFile(arquivo, ConstantesEmail.getCodificacaoJvmPadrao());
            } else {
                conteudo = EmailUtils.readTextFile(arquivo, codificacaoLeitura);
            }
            if (codificacao == null) {
                return MimeUtility.encodeText(conteudo);
            } else if (codificacao.isEmpty()) {
                return MimeUtility.encodeText(conteudo, ConstantesEmail.getCodificacaoJvmPadrao(), ConstantesEmail.getCodificacaoRfc822Padrao());
            } else {
                return MimeUtility.encodeText(conteudo, codificacao, null);
            }
        }
        return null;
    }

    public String gerarDadosConteudo() throws FileNotFoundException, IOException {
        if (this.dados != null) {
            return this.dados;
        }
        if (arquivo != null) {
            String conteudo;
            String codificacaoLeitura = codificacaoOriginal == null ? codificacao : codificacaoOriginal;
            if (codificacaoLeitura == null) {
                conteudo = EmailUtils.readTextFile(arquivo);
            } else if (codificacaoLeitura.isEmpty()) {
                conteudo = EmailUtils.readTextFile(arquivo, ConstantesEmail.getCodificacaoJvmPadrao());
            } else {
                conteudo = EmailUtils.readTextFile(arquivo, codificacaoLeitura);
            }
            return conteudo;
        }
        return null;
    }
    
    public byte[] gerarDadosBinario() throws FileNotFoundException, IOException {
        byte[] array = null;
        String codificacaoSaida = gerarCodificacao();
        if (this.dados != null) {
            array = this.dados.getBytes(codificacaoSaida);
        } else if (this.arquivo != null) {
            array = new byte[(int) arquivo.length()];
            FileInputStream fis = new FileInputStream(arquivo);
            fis.read(array);
            fis.close();
            if (this.codificacaoOriginal != null) {
                array = new String(array, this.codificacaoOriginal).getBytes(codificacaoSaida);
            }
        }
        return array;
    }
    
    public String getCodificacao() {
        return codificacao;
    }

    public void setCodificacao(String codificacao) {
        this.codificacao = codificacao;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public TipoDados getTipoDados() {
        return tipoDados;
    }

    public void setTipoDados(TipoDados tipoDados) {
        this.tipoDados = tipoDados;
    }

    public String getCodificacaoOriginal() {
        return codificacaoOriginal;
    }

    public void setCodificacaoOriginal(String codificacaoOriginal) {
        this.codificacaoOriginal = codificacaoOriginal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
