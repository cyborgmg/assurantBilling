package br.com.delphos.util.email;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class Email {

    private String codificacaoPadrao = ConstantesEmail.CodificacaoEnvio;
    private DadosEmail remetente = new DadosEmail(codificacaoPadrao);
    private DadosEmail destinatario = new DadosEmail(codificacaoPadrao);
    private DadosEmail assunto = new DadosEmail(codificacaoPadrao);
    private DadosEmail mensagem = new DadosEmail(codificacaoPadrao);
    private List<DadosEmail> copias = new ArrayList<DadosEmail>();
    private List<DadosEmail> anexos = new ArrayList<DadosEmail>();

    public Email() {
    }

    public Email(String codificacaoPadrao) {
        this.codificacaoPadrao = codificacaoPadrao;
        this.remetente = new DadosEmail(codificacaoPadrao);
        this.destinatario = new DadosEmail(codificacaoPadrao);
        this.assunto = new DadosEmail(codificacaoPadrao);
        this.mensagem = new DadosEmail(codificacaoPadrao);
    }

    public static Email novoEmail() {
    	Email email = new Email();
    	return email;
    }

    public static Email novoEmail(String codificacao) {
    	Email email = new Email(codificacao);
    	return email;
    }
    
    public Email addCopia(String email, String... args) {
        DadosEmail dados = DadosEmail.campo(email, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        copias.add(dados);
        return this;
    }
    
    public Email addRemetente(String email, String... args) {
        DadosEmail dados = DadosEmail.campo(email, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        this.remetente = dados;
        return this;
    }
    
    public Email addDestinatario(String email, String... args) {
        DadosEmail dados = DadosEmail.campo(email, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        this.destinatario = dados;
        return this;
    }
    
    public Email addAssunto(String str, String... args) {
        DadosEmail dados = DadosEmail.campo(str, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        this.assunto = dados;
        return this;
    }
    
    public Email addMensagemTexto(String msg, String... args) {
        DadosEmail dados = DadosEmail.conteudoTexto(msg, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        this.mensagem = dados;
        return this;
    }
    
    public Email addMensagemHtml(String msg, String... args) {
        DadosEmail dados = DadosEmail.conteudoHtml(msg, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        this.mensagem = dados;
        return this;
    }
    
    public Email addAnexoArquivoTexto(File arquivo, String... args) {
        DadosEmail dados = DadosEmail.arquivoTexto(arquivo, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        anexos.add(dados);
        return this;
    }
    
    public Email addAnexoArquivoBinario(File arquivo, String... args) {
        DadosEmail dados = DadosEmail.arquivoBinario(arquivo, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        anexos.add(dados);
        return this;
    }
    
    public Email addAnexoTexto(String conteudo, String... args) {
        DadosEmail dados = DadosEmail.conteudoTexto(conteudo, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        anexos.add(dados);
        return this;
    }
    
    public Email addAnexoHtml(String conteudo, String... args) {
        DadosEmail dados = DadosEmail.conteudoHtml(conteudo, args);
        if (args.length == 0) {
            dados.setCodificacao(codificacaoPadrao);
        }
        anexos.add(dados);
        return this;
    }

    public void preparar(Message msg) throws EmailException {
        if (!isValido()) {
            throw new EmailException("Dados de e-mail inválidos.");
        }
        
        try {
            msg.setFrom(new InternetAddress(this.remetente.gerarDadosCampo()));

            if (this.destinatario != null) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.destinatario.gerarDadosCampo()));
            }

            if (this.assunto != null) {
                String data = this.assunto.gerarDadosCampo();
                msg.setSubject(data);
            }

            if (!copias.isEmpty()) {
                Address[] copiados = new Address[copias.size()];
                int i = 0;
                for (DadosEmail copia : copias) {
                    copiados[i] = new InternetAddress(copia.gerarDadosCampo());
                    i++;
                }
                msg.addRecipients(Message.RecipientType.CC, copiados);
            }
            
            if (getTipoEnvelope() == TipoEnvelope.Multipart) {
                int idx = 0;
                Multipart mps = new MimeMultipart();
                
                for (DadosEmail anexo : anexos) {
                    MimeBodyPart anexoBodyPart = new MimeBodyPart();
                    String mimeType = anexo.gerarMimeType();
                    String codificacaoGerada = anexo.gerarCodificacao();
                    
                    if (anexo.isArquivo()) {
                        if (anexo.getCodificacao() != null) {
                            byte[] dadosAnexo = anexo.gerarDadosBinario();
                            ByteArrayDataSource anexoDataSource = new ByteArrayDataSource(dadosAnexo, mimeType);
                            anexoBodyPart.setDataHandler(new DataHandler(anexoDataSource));
                            anexoBodyPart.setDisposition(Part.ATTACHMENT);
                            anexoBodyPart.setFileName(anexo.getArquivo().getName());
                            mps.addBodyPart(anexoBodyPart);
                            
                        } else {
                            FileDataSource fds = new FileDataSource(anexo.getArquivo());
                            anexoBodyPart.setDataHandler(new DataHandler(fds));
                            anexoBodyPart.setFileName(fds.getName());
                            mps.addBodyPart(anexoBodyPart, idx++);
                        }

                    } else {
                        if (anexo.getTipoDados() == TipoDados.BINARIO) {
                            byte[] dadosAnexo = anexo.gerarDadosBinario();
                            String nomeAnexo=  anexo.getNome() != null ? anexo.getNome() : String.format("Attachment-%s", idx);
                            ByteArrayDataSource fds = new ByteArrayDataSource(dadosAnexo, mimeType);
                            anexoBodyPart.setDataHandler(new DataHandler(fds));
                            anexoBodyPart.setFileName(nomeAnexo);
                            mps.addBodyPart(anexoBodyPart, idx++);
                            
                        } else {
                            String conteudoAnexo = anexo.gerarDadosConteudo();
                            //String mimeType = anexo.gerarMimeType();
                            File arquivoTemporarioAnexo = File.createTempFile("Attachment-", ".txt");
                            Writer output = new OutputStreamWriter(new FileOutputStream(arquivoTemporarioAnexo), codificacaoGerada);
                            output.append(conteudoAnexo);
                            output.close();
                            FileDataSource fds = new FileDataSource(arquivoTemporarioAnexo);
                            anexoBodyPart.setDataHandler(new DataHandler(fds));
                            anexoBodyPart.setFileName(arquivoTemporarioAnexo.getName());
                            mps.addBodyPart(anexoBodyPart, idx++);
                        }
                    }
                }

                MimeBodyPart textPart = new MimeBodyPart();
                String mimeType = mensagem.gerarMimeType();
                if (mensagem.getTipoDados() == TipoDados.BINARIO) {
                    textPart.setContent(mensagem.gerarDadosBinario(), mimeType);
                } else {
                    textPart.setContent(mensagem.gerarDadosConteudo(), mimeType);
                }
                mps.addBodyPart(textPart);
                msg.setContent(mps);
                
            } else {
                String mimeType = mensagem.gerarMimeType();
                if (mensagem.getTipoDados() == TipoDados.BINARIO) {
                    msg.setContent(mensagem.gerarDadosBinario(), mimeType);
                } else {
                    msg.setContent(mensagem.gerarDadosConteudo(), mimeType);
                }
            }
            
        } catch (MessagingException ex) {
            throw new EmailException("Erro ao enviar e-mail.", ex);
            
        } catch (UnsupportedEncodingException ex) {
            throw new EmailException("Erro ao enviar e-mail.", ex);
            
        } catch (FileNotFoundException ex) {
            throw new EmailException("Erro ao enviar e-mail.", ex);
            
        } catch (IOException ex) {
            throw new EmailException("Erro ao enviar e-mail.", ex);
        }
    }

    public boolean isValido() {
        boolean valido = true;
        valido &= remetente != null && remetente.isValido();
        valido &= destinatario != null && destinatario.isValido();
        valido &= assunto == null || assunto.isCampo();
        valido &= mensagem != null && mensagem.isValido();
        valido &= copias != null && anexos != null;
        if (valido) {
            for (DadosEmail data : this.copias) {
                valido &= data.isCampo();
                if (!valido) {
                    break;
                }
            }
        }
        if (valido) {
            for (DadosEmail data : this.anexos) {
                valido &= data.isValido();
                if (!valido) {
                    break;
                }
            }
        }
        return valido;
    }

    public TipoEnvelope getTipoEnvelope() {
        return anexos.isEmpty() ? TipoEnvelope.Simples : TipoEnvelope.Multipart;
    }

    public List<DadosEmail> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<DadosEmail> anexos) {
        this.anexos = anexos;
    }

    public DadosEmail getAssunto() {
        return assunto;
    }

    public void setAssunto(DadosEmail assunto) {
        this.assunto = assunto;
    }

    public String getCodificacaoPadrao() {
        return codificacaoPadrao;
    }

    public void setCodificacaoPadrao(String codificacaoPadrao) {
        this.codificacaoPadrao = codificacaoPadrao;
    }

    public List<DadosEmail> getCopias() {
        return copias;
    }

    public void setCopias(List<DadosEmail> copias) {
        this.copias = copias;
    }

    public DadosEmail getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(DadosEmail destinatario) {
        this.destinatario = destinatario;
    }

    public DadosEmail getMensagem() {
        return mensagem;
    }

    public void setMensagem(DadosEmail mensagem) {
        this.mensagem = mensagem;
    }

    public DadosEmail getRemetente() {
        return remetente;
    }

    public void setRemetente(DadosEmail remetente) {
        this.remetente = remetente;
    }

}
