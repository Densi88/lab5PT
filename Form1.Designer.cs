namespace lab5
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            components = new System.ComponentModel.Container();
            mainPb = new PictureBox();
            timer1 = new System.Windows.Forms.Timer(components);
            txtLog = new RichTextBox();
            scoreLabel = new Label();
            ((System.ComponentModel.ISupportInitialize)mainPb).BeginInit();
            SuspendLayout();
            // 
            // mainPb
            // 
            mainPb.Location = new Point(-6, 1);
            mainPb.Name = "mainPb";
            mainPb.Size = new Size(804, 450);
            mainPb.TabIndex = 0;
            mainPb.TabStop = false;
            mainPb.Paint += mainPb_paint;
            mainPb.MouseClick += mainPb_MouseClick;
            // 
            // timer1
            // 
            timer1.Enabled = true;
            timer1.Tick += timer1_Tick;
            // 
            // txtLog
            // 
            txtLog.Location = new Point(804, 1);
            txtLog.Name = "txtLog";
            txtLog.Size = new Size(197, 450);
            txtLog.TabIndex = 1;
            txtLog.Text = "";
            // 
            // scoreLabel
            // 
            scoreLabel.AutoSize = true;
            scoreLabel.Location = new Point(731, 23);
            scoreLabel.Name = "scoreLabel";
            scoreLabel.Size = new Size(45, 15);
            scoreLabel.TabIndex = 2;
            scoreLabel.Text = "Счет: 0";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1013, 482);
            Controls.Add(scoreLabel);
            Controls.Add(txtLog);
            Controls.Add(mainPb);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)mainPb).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private PictureBox mainPb;
        private System.Windows.Forms.Timer timer1;
        private RichTextBox txtLog;
        private Label scoreLabel;
    }
}