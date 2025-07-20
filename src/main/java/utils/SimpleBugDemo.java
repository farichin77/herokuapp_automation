package utils;

import java.io.File;

public class SimpleBugDemo {
    
    public static void main(String[] args) {
        System.out.println("🐛 Creating Bug Report Demo...");
        
        // Clear any existing reports
        BugReporter.clearBugReports();
        
        // Create sample bug reports
        createSampleBugs();
        
        // Generate HTML report
        BugReporter.generateBugReport();
        
        // Show results
        showResults();
    }
    
    private static void createSampleBugs() {
        System.out.println("📝 Creating sample bug reports...");
        
        // Critical Bug
        BugReporter.reportCriticalBug(
            "Login System Crash",
            "Aplikasi crash ketika user login dengan username kosong",
            "Sistem harus menampilkan error message yang jelas",
            "Aplikasi crash dengan NullPointerException",
            "1. Buka halaman login\n2. Kosongkan field username\n3. Isi password\n4. Klik login\n5. Aplikasi crash",
            null
        );
        
        // High Priority Bug
        BugReporter.reportHighBug(
            "Payment Processing Error",
            "Pembayaran gagal tapi tidak ada notifikasi error",
            "User harus mendapat notifikasi jika pembayaran gagal",
            "Pembayaran gagal tapi user tidak tahu, loading terus",
            "1. Tambah item ke cart\n2. Checkout\n3. Masukkan kartu kredit invalid\n4. Submit payment\n5. Loading tidak berhenti",
            null
        );
        
        // Medium Priority Bug
        BugReporter.reportMediumBug(
            "Search Results Issue",
            "Hasil pencarian tidak sesuai dengan keyword",
            "Hasil pencarian harus relevan dengan keyword yang dimasukkan",
            "Pencarian 'laptop' menampilkan hasil 'handphone'",
            "1. Masuk ke halaman search\n2. Ketik 'laptop'\n3. Klik search\n4. Lihat hasil yang tidak relevan",
            null
        );
        
        // Low Priority Bug
        BugReporter.reportLowBug(
            "Footer Copyright Year",
            "Copyright di footer masih menampilkan tahun 2022",
            "Copyright harus menampilkan tahun saat ini (2025)",
            "Footer menampilkan '© 2022 Company' bukan '© 2025 Company'",
            "1. Scroll ke bawah halaman\n2. Lihat footer\n3. Copyright year masih 2022",
            null
        );
        
        System.out.println("✅ Created " + BugReporter.getBugCount() + " bug reports");
    }
    
    private static void showResults() {
        System.out.println("\n📊 Bug Report Summary:");
        System.out.println("Total bugs reported: " + BugReporter.getBugCount());
        
        // Check if bug report file was created
        File bugReportFile = new File("bug-reports/bug-report.html");
        if (bugReportFile.exists()) {
            System.out.println("✅ Bug report file created: " + bugReportFile.getAbsolutePath());
            System.out.println("📂 File size: " + bugReportFile.length() + " bytes");
        } else {
            System.out.println("❌ Bug report file not found");
        }
        
        System.out.println("\n🌐 To view the bug report:");
        System.out.println("1. Open file: bug-reports/bug-report.html");
        System.out.println("2. Double-click to open in browser");
        System.out.println("3. Or drag & drop to browser window");
    }
}
